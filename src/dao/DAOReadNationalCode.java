package dao;

import java.util.Optional;

public interface DAOReadNationalCode<T> {
    Optional<T> getByNationalCode(String nationalCode);
}
