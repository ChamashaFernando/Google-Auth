package lk.chamasha.googleauth.service;

import lk.chamasha.googleauth.controller.request.GoogleLoginRequest;
import lk.chamasha.googleauth.controller.response.AuthResponse;

public interface AuthService {

    AuthResponse googleLogin(GoogleLoginRequest request);
}