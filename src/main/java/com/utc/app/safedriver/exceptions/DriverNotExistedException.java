package com.utc.app.safedriver.exceptions;

public class DriverNotExistedException extends Exception {
    public DriverNotExistedException(String message) {
        super(message);
    }
}