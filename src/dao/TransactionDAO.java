package dao;

import model.Transaction;

import java.util.List;
import java.util.Optional;

public class TransactionDAO implements DAO<Transaction>,DAOReadId<Transaction> {
    @Override
    public List<Transaction> getAll() {
        return null;
    }

    @Override
    public void save(Transaction transaction) {

    }

    @Override
    public void update(Transaction transaction) {

    }

    @Override
    public void delete(Transaction transaction) {

    }

    @Override
    public Optional<Transaction> getById(Integer id) {
        return Optional.empty();
    }
}
