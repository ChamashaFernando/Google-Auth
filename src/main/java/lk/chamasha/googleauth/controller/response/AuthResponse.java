package lk.chamasha.googleauth.controller.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String jwtToken;
    private String email;
    private String name;
    private String role;
    private String profilePicture;
}