package com.utc.app.safedriver.exceptions;

public class UserNotExistedException extends Exception {
    public UserNotExistedException(String message) {
        super(message);
    }
}
