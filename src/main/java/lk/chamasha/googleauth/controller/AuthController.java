package lk.chamasha.googleauth.controller;

import lk.chamasha.googleauth.controller.request.AuthResponse;
import lk.chamasha.googleauth.controller.request.GoogleLoginRequest;
import lk.chamasha.googleauth.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/google")
    public ResponseEntity<AuthResponse> googleLogin(@RequestBody GoogleLoginRequest request) {

        AuthResponse response = authService.googleLogin(request);

        return ResponseEntity.ok(response);
    }
}