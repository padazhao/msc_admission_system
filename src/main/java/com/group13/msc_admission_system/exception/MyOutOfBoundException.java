package com.group13.msc_admission_system.exception;

/**
 * A custom exception class to handle out of bounds errors
 */

public class MyOutOfBoundException extends RuntimeException{
    public MyOutOfBoundException(String message) {
        super(message);
    }
}
