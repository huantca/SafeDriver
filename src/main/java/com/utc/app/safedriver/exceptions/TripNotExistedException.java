package com.utc.app.safedriver.exceptions;

public class TripNotExistedException extends Exception {
    public TripNotExistedException(String message) {
        super(message);
    }
}
