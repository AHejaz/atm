package dao;

import database.Database;
import model.Account;
import model.User;
import model.enums.AccountType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AccountDAO  implements DAO<Account>,DAOReadNationalCode<Account> {

    private Connection database;

    public AccountDAO()  {
        database = Database.getInstance();
    }

    @Override
    public Optional<Account> getByNationalCode(String nationalCode) {
        Account account ;
        User user;
        try {
            PreparedStatement ps = database.prepareStatement("select * from accounts where national_code = ?");
            ps.setString(1,nationalCode);
            ResultSet output =  ps.executeQuery();
            if (output.first()){
                user = new User(output.getString("name"),output.getString("family"),
                        output.getString("national_code"),output.getObject("birth_day", LocalDate.class));

                account = new Account(user,output.getString("password"),output.getObject("account_type", AccountType.class));
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
}
