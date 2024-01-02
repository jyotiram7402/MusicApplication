package org.gfg.musicapplication.requests;

import lombok.*;
import org.gfg.musicapplication.model.User;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignupRequest {

    private String username;

    @NotBlank(message = "user email should not be blank.")
    private String userEmail;

    private String phone;

    private String password;

    public User toUser() {
        return User.builder().
                name(username).
                mail(this.userEmail).
                phone(this.phone).
                build();
    }
}
