package lk.chamasha.googleauth.service.impl;

import lk.chamasha.googleauth.controller.request.AuthResponse;
import lk.chamasha.googleauth.controller.request.GoogleLoginRequest;
import lk.chamasha.googleauth.model.Role;
import lk.chamasha.googleauth.model.User;
import lk.chamasha.googleauth.repository.UserRepository;
import lk.chamasha.googleauth.security.JwtService;
import lk.chamasha.googleauth.service.AuthService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponse googleLogin(GoogleLoginRequest request) {

        // 1. Google ID Token verify (simplified)
        String googleId = extractGoogleId(request.getIdToken());
        String email = extractEmail(request.getIdToken());
        String name = extractName(request.getIdToken());
        String picture = extractPicture(request.getIdToken());

        // 2. Check user exists
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .googleId(googleId)
                            .email(email)
                            .name(name)
                            .profilePicture(picture)
                            .role(Role.EMPLOYEE)
                            .build();

                    return userRepository.save(newUser);
                });

        // 3. Generate JWT
        String jwt = jwtService.generateToken(user);

        // 4. Response
        return AuthResponse.builder()
                .jwtToken(jwt)
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().name())
                .profilePicture(user.getProfilePicture())
                .build();
    }

    // 🔽 Dummy extract methods (later replace with Google verification)
    private String extractGoogleId(String token) {
        return "google-id";
    }

    private String extractEmail(String token) {
        return "user@gmail.com";
    }

    private String extractName(String token) {
        return "Google User";
    }

    private String extractPicture(String token) {
        return "profile.jpg";
    }
}