package com.utc.app.safedriver.email;

import com.utc.app.safedriver.exceptions.AuthenticationException;

public class EmailAlreadyExistedException extends AuthenticationException {
    public EmailAlreadyExistedException(String message) {
        super(message);
    }
}