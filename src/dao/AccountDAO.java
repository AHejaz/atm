package dao;

import database.Database;
import model.Account;
import model.Card;
import model.Transaction;
import model.User;
import model.enums.AccountType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAO  implements DAO<Account>,DAOReadCardNumber<Account> {

    private TransactionDAO transactionDAO;
    private CardDAO cardDAO;
    private UserDao userDao;

    private Connection database;

    public AccountDAO()  {
        database = Database.getInstance();
        cardDAO= new CardDAO();
        transactionDAO = new TransactionDAO();
        userDao = new UserDao();
    }

    @Override
    public Optional<Account> getByCardNumber(String nationalCode) {
        Account account = null;
        User user;
        Card card;
        List<Transaction> transactionList = new ArrayList<>();
        try {
            PreparedStatement ps = database.prepareStatement("select *from accounts ac inner join" +
                    " users u on ac.id = u.account_id where card_number= ?");
            ps.setString(1,nationalCode);
            ResultSet output =  ps.executeQuery();
            if (output.first()){
                user = new User(output.getInt("u.id"),output.getString("name"),output.getString("family"),
                        output.getString("national_code"),output.getObject("birth_day", LocalDate.class));

                account =new Account(output.getInt("ac.id"),user,
                        output.getString("account_number"),output.getString("password"),
                        output.getDouble("balance"),output.getObject("account_type",AccountType.class),
                        null,null);
                transactionList = transactionDAO.getById(account.getId());
                card = cardDAO.getById(account.getId()).orElseThrow();
                account.setCard(card);
                account.setTransactions(transactionList);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }
        return Optional.of(account);
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            PreparedStatement ps = database.prepareStatement("select * from accounts");
            ResultSet output = ps.executeQuery();
            while (output.next()){
                Card card=cardDAO.getById(output.getInt("id")).orElseThrow();
                List<Transaction> transactions = transactionDAO.getById(output.getInt("id"));
                User user = userDao.getById(output.getInt("id")).orElseThrow();
                Account account = new Account(output.getInt("id"),user,
                        output.getString("account_number"),output.getString("password"),
                        output.getDouble("amount"),output.getObject("account_type",AccountType.class),
                        card,transactions);
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }
        return accounts;
    }

    @Override
    public void save(Account account) {
        try {
            PreparedStatement ps = database.prepareStatement("insert into accounts(id,account_number," +
                    "password,balance,account_type) values (null,?,?,?,?)");
            ps.setString(1,account.getAccountNumber());
            ps.setString(2,account.getPassword());
            ps.setDouble(3,account.getBalance());
            ps.setObject(4,account.getType());
            ps.execute();
            PreparedStatement ps2 = database.prepareStatement("select * from  accounts order by id desc limit 1");
            ResultSet output = ps.executeQuery();
            if (output.first()){
                userDao.save(account.getUser(),output.getInt("id"));
                cardDAO.save(account.getCard(),output.getInt("id"));
                transactionDAO.save(account.getTransactions(),output.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Account account) {
        try {
            PreparedStatement ps = database.prepareStatement("update accounts set account_number=?,password=?," +
                    "balance=?,account_type=? where id = ?");
            ps.setString(1,account.getAccountNumber());
            ps.setString(2,account.getPassword());
            ps.setDouble(3,account.getBalance());
            ps.setObject(4,account.getType());
            ps.setInt(5,account.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }

    }

    @Override
    public void delete(Account account) {
        try {
            PreparedStatement ps = database.prepareStatement("delete from accounts where id=?");
            ps.setInt(1,account.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
