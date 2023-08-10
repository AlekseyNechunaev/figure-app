package ru.fc2.figure.exception;

public class InputDataValidationException extends RuntimeException {

    public InputDataValidationException() {

    }

    public InputDataValidationException(String message) {
        super(message);
    }

    public InputDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputDataValidationException(Throwable cause) {
        super(cause);
    }
}
