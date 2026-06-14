package lk.chamasha.googleauth.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import lk.chamasha.googleauth.controller.response.GoogleUserInfo;
import lk.chamasha.googleauth.exception.InvalidGoogleTokenException;
import lk.chamasha.googleauth.service.GoogleTokenVerifierService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleTokenVerifierServiceImpl
        implements GoogleTokenVerifierService {

    @Value("${google.client.id}")
    private String clientId;

    @Override
    public GoogleUserInfo verifyToken(String idTokenString) {

        try {

            GoogleIdTokenVerifier verifier =
                    new GoogleIdTokenVerifier.Builder(
                            new NetHttpTransport(),
                            GsonFactory.getDefaultInstance()
                    )
                            .setAudience(
                                    Collections.singletonList(clientId)
                            )
                            .build();

            GoogleIdToken idToken =
                    verifier.verify(idTokenString);

            if (idToken == null) {
                throw new InvalidGoogleTokenException(
                        "Invalid Google Token"
                );
            }

            GoogleIdToken.Payload payload =
                    idToken.getPayload();

            return GoogleUserInfo.builder()
                    .googleId(payload.getSubject())
                    .email(payload.getEmail())
                    .name((String) payload.get("name"))
                    .picture((String) payload.get("picture"))
                    .build();

        } catch (Exception ex) {

            throw new InvalidGoogleTokenException(
                    "Google token verification failed"
            );
        }
    }
}