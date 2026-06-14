package lk.chamasha.googleauth.service;

import lk.chamasha.googleauth.controller.request.AuthResponse;
import lk.chamasha.googleauth.controller.request.GoogleLoginRequest;


public interface AuthService {
    AuthResponse googleLogin(GoogleLoginRequest request);
}