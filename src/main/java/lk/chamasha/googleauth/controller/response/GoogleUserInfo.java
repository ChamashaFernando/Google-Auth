package lk.chamasha.googleauth.controller.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUserInfo {

    private String googleId;
    private String email;
    private String name;
    private String picture;
}