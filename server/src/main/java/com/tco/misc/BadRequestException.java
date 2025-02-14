package com.tco.misc;
/*
 * This is a custom exception that fits our personal
 * needs and won't collide with existing issues.
 */
public class BadRequestException extends Exception {
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message) {
        super(message);
    }
}
