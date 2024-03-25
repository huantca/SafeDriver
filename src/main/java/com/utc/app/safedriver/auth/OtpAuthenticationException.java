package com.utc.app.safedriver.auth;

import com.utc.app.safedriver.exceptions.AuthenticationException;

public class OtpAuthenticationException extends AuthenticationException {
    public OtpAuthenticationException(String message) {
        super(message);
    }
}
