package Exceptions;

import file.MessageLoader;

public class InvalidAmountException extends RuntimeException{
    public static final String message = MessageLoader.getMessage("INVALID_AMOUNT");

    public InvalidAmountException() {
        super(message);
    }
}
