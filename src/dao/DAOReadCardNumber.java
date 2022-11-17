package dao;

import java.util.Optional;

public interface DAOReadCardNumber <T>{
    Optional<T> getByCardNumber(String cardNumber);
}
