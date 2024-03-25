package com.utc.app.safedriver.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String otp;
    private String phone;

    public void fillBlankValue() {
        name = name == null || name.isBlank() ? email.split("@")[0] : name;
    }
}
