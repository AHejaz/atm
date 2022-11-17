package dao;

import database.Database;
import model.Transaction;
import model.enums.TransactionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDAO implements DAO<Transaction> {
    private Connection database;

    public TransactionDAO() {
        database = Database.getInstance();
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            PreparedStatement ps = database.prepareStatement("select * from transactions");
            ResultSet output = ps.executeQuery();
            while (output.next()) {
                Transaction transaction = new Transaction(output.getInt("id"), output.getDouble("amount"),
                        output.getObject("transaction_type", TransactionType.class), output.getDate("date"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Database.close();
        }

        return transactions;
    }

    @Override
    public void save(Transaction transaction) {
        try {
            PreparedStatement ps = database.prepareStatement("insert into transactions(id,amount,transaction_type,date) " +
                    "values (null,?,?,?)");
            ps.setDouble(1, transaction.getAmount());
            ps.setObject(2, transaction.getTransactionType());
            ps.setObject(3, transaction.getDate());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Transaction transaction) {
        try {
            PreparedStatement ps = database.prepareStatement("update transactions set amount=?,transaction_type=?,date=? " +
                    "where id = ?");
            ps.setDouble(1, transaction.getAmount());
            ps.setObject(2, transaction.getTransactionType());
            ps.setObject(3, transaction.getDate());
            ps.setInt(4, transaction.getId());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Database.close();
        }

    }

    @Override
    public void delete(Transaction transaction) {
        try {
            PreparedStatement ps = database.prepareStatement("delete from transactions where id=?");
            ps.setInt(1, transaction.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Database.close();
        }


    }


    public List<Transaction> getById(Integer id) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            PreparedStatement ps = database.prepareStatement("select * from transactions where account_id=?");
            ps.setInt(1, id);
            ResultSet output = ps.executeQuery();
            while (output.next()) {
                Transaction transaction = new Transaction(output.getInt("id"), output.getDouble("amount"),
                        output.getObject("transaction_type", TransactionType.class), output.getDate("date"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Database.close();
        }
        return transactions;
    }
}
