package util;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Util {
    public static void print(Object o){
        System.out.println(o);
    }

    public static <T> Object getInput(String message,T c){
        print(message);
        Scanner scanner = new Scanner(System.in);
        try {
            if (c== int.class || c == Integer.class)
                return scanner.nextInt();
            if (c== String.class)
                return scanner.next();
            if (c== double.class || c == Double.class)
                return scanner.nextDouble();
            if (c== float.class || c == Float.class)
                return scanner.nextFloat();
            if (c== boolean.class || c == Boolean.class)
                return scanner.nextBoolean();
        }catch (InputMismatchException e){
            return getInput("Not valid.",c);
        }
        return null;
    }

}
