package org.kivio.stt.user.adapter.http.impl;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
