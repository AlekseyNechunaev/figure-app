package ru.fc2.figure.exception;

public class CommandLineValidationException extends RuntimeException {

    public CommandLineValidationException() {
    }

    public CommandLineValidationException(String message) {
        super(message);
    }
}
