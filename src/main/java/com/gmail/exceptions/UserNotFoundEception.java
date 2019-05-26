package com.gmail.exceptions;

/**
 * Created by Space on 26.05.2019.
 */
public class UserNotFoundEception extends Exception {
    public UserNotFoundEception() {
    }

    public UserNotFoundEception(String message) {
        super(message);
    }
}
