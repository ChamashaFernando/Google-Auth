//package lk.chamasha.googleauth.service.impl;
//
//import lk.chamasha.googleauth.controller.request.GoogleLoginRequest;
//import lk.chamasha.googleauth.controller.response.AuthResponse;
//import lk.chamasha.googleauth.controller.response.GoogleUserInfo;
//import lk.chamasha.googleauth.model.Role;
//import lk.chamasha.googleauth.model.User;
//import lk.chamasha.googleauth.repository.UserRepository;
//import lk.chamasha.googleauth.security.JwtService;
//import lk.chamasha.googleauth.service.AuthService;
//import lk.chamasha.googleauth.service.GoogleTokenVerifierService;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//    private final UserRepository userRepository;
//    private final JwtService jwtService;
//    private final GoogleTokenVerifierService googleTokenVerifierService;
//
//    @Override
//    public AuthResponse googleLogin(GoogleLoginRequest request) {
//
//        // Verify Google ID Token
//        GoogleUserInfo googleUser =
//                googleTokenVerifierService.verifyToken(
//                        request.getIdToken()
//                );
//
//        // Check if user already exists
//        User user = userRepository.findByEmail(
//                        googleUser.getEmail()
//                )
//                .orElseGet(() -> {
//
//                    User newUser = User.builder()
//                            .googleId(googleUser.getGoogleId())
//                            .name(googleUser.getName())
//                            .email(googleUser.getEmail())
//                            .profilePicture(
//                                    googleUser.getPicture()
//                            )
//                            .role(Role.EMPLOYEE)
//                            .build();
//
//                    return userRepository.save(newUser);
//                });
//
//        // Generate JWT
//        String jwtToken =
//                jwtService.generateToken(user);
//
//        // Build Response
//        return AuthResponse.builder()
//                .jwtToken(jwtToken)
//                .email(user.getEmail())
//                .name(user.getName())
//                .role(user.getRole().name())
//                .profilePicture(user.getProfilePicture())
//                .build();
//    }
//}


package lk.chamasha.googleauth.service.impl;

import lk.chamasha.googleauth.controller.request.GoogleLoginRequest;
import lk.chamasha.googleauth.controller.response.AuthResponse;
import lk.chamasha.googleauth.controller.response.GoogleUserInfo;
import lk.chamasha.googleauth.exception.InvalidGoogleTokenException;
import lk.chamasha.googleauth.model.Role;
import lk.chamasha.googleauth.model.User;
import lk.chamasha.googleauth.repository.UserRepository;
import lk.chamasha.googleauth.security.JwtService;
import lk.chamasha.googleauth.service.AuthService;
import lk.chamasha.googleauth.service.GoogleTokenVerifierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final GoogleTokenVerifierService googleTokenVerifierService;

    @Override
    public AuthResponse googleLogin(GoogleLoginRequest request) {

        if (request == null || request.getIdToken() == null
                || request.getIdToken().isBlank()) {

            throw new InvalidGoogleTokenException(
                    "Google ID Token is required"
            );
        }

        // Verify Google Token
        GoogleUserInfo googleUser =
                googleTokenVerifierService.verifyToken(
                        request.getIdToken()
                );

        if (googleUser.getEmail() == null
                || googleUser.getEmail().isBlank()) {

            throw new InvalidGoogleTokenException(
                    "Email not found in Google account"
            );
        }

        // Find existing user or create new user
        User user = userRepository.findByEmail(
                        googleUser.getEmail()
                )
                .orElseGet(() -> {

                    User newUser = User.builder()
                            .googleId(googleUser.getGoogleId())
                            .name(googleUser.getName())
                            .email(googleUser.getEmail())
                            .profilePicture(
                                    googleUser.getPicture()
                            )
                            .role(Role.EMPLOYEE)
                            .build();

                    return userRepository.save(newUser);
                });

        // Generate JWT
        String jwtToken = jwtService.generateToken(user);

        // Return Response
        return AuthResponse.builder()
                .jwtToken(jwtToken)
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().name())
                .profilePicture(user.getProfilePicture())
                .build();
    }
}