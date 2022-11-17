package Exceptions;

import file.MessageLoader;

public class AgeException extends RuntimeException{
//    public static final String Message = "you are underage!";
    public static final String message = MessageLoader.getMessage("AGE");
    public AgeException(){
        super(message);
    }
}
