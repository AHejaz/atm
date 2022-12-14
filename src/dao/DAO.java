package dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    List<T> getAll();

    void save(T t);


    void update(T t);

    void delete(T t);
}
