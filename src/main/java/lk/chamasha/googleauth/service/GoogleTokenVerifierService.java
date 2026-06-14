package lk.chamasha.googleauth.service;

import lk.chamasha.googleauth.controller.response.GoogleUserInfo;

public interface GoogleTokenVerifierService {

    GoogleUserInfo verifyToken(String idToken);
}