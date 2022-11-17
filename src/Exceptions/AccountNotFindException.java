package Exceptions;

import file.MessageLoader;

public class AccountNotFindException extends RuntimeException{
//    public static final String message = "model.Account not found";
    public static final String message = MessageLoader.getMessage("ACCOUNT_NOT_FOUND");

    public AccountNotFindException() {
        super(message);
    }
}
