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
import java.util.List;
import java.util.Optional;

public class AccountDAO  implements DAO<Account>,DAOReadCardNumber<Account>,DAOReadId<Account> {

    private TransactionDAO transactionDAO;
    private CardDAO cardDAO;

    private Connection database;

    public AccountDAO()  {
        database = Database.getInstance();
        cardDAO= new CardDAO();
        transactionDAO = new TransactionDAO();
    }

    @Override
    public Optional<Account> getByCardNumber(String nationalCode) {
        Account account ;
        User user;
        Card card;
        List<Transaction> transactionList;
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Account> getAll() {
        try {
            PreparedStatement ps = database.prepareStatement("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void save(Account account) {

    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public Optional<Account> getById(Integer id) {
        return Optional.empty();
    }
}
