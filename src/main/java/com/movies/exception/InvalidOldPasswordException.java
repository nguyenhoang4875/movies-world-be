package com.movies.exception;

public class InvalidOldPasswordException extends RuntimeException {
    public InvalidOldPasswordException(final String message) {
        super(message);
    }
}
