import controller.ATM;

import java.io.IOException;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Runner {

    public static void main(String[] args) throws IOException {

        ATM atm = new ATM();
        atm.run();
    }
}