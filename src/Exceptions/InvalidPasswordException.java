package Exceptions;

import file.MessageLoader;

public class InvalidPasswordException extends RuntimeException {
    public static final String message = MessageLoader.getMessage("INVALID_PASSWORD");

    public InvalidPasswordException() {
        super(message);
    }
}
