package dao;

import java.util.Optional;

public interface DAOReadId<T> {
    Optional<T> getById(Integer id);
}
