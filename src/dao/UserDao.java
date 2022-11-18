package dao;

import database.Database;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements DAO<User>,DAOReadNationalCode<User>,DAOReadId<User>{
    private Connection database;

    public UserDao() {
            database = Database.getInstance();
    }

    @Override
    public Optional<User> getByNationalCode(String nationalCode) {
        User user = null;
        try {
            PreparedStatement ps = database.prepareStatement("select * from users where national_code = ?");
            ps.setString(1,nationalCode);
            ResultSet output =ps.executeQuery();
            if (output.first()){
                user = new User(output.getInt("id"),output.getString("name"),
                        output.getString("family"),output.getString("national_code"),
                        output.getObject("birth_day", LocalDate.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            return Optional.of(user);
        }

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = database.prepareStatement("select * from users");
            ResultSet output = ps.executeQuery();
            while (output.next()){
                User user = new User(output.getInt("id"),output.getString("name"),
                        output.getString("family"),output.getString("national_code"),
                        output.getObject("birth_day", LocalDate.class));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }
        return users;
    }

    @Override
    public void save(User user) {
        try {
            PreparedStatement ps = database.prepareStatement("insert  into users(id,name,family,age,national_code,birth_day)" +
                    "values (null,?,?,?,?,?) ");
            ps.setString(1,user.getName());
            ps.setString(2,user.getFamily());
            ps.setInt(3,user.getAge());
            ps.setString(4,user.getNationalCode());
            ps.setObject(5,user.getBirthday());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }

    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement ps = database.prepareStatement("update users set name=?,family=?,age=?,national_code=?,birth_day=?" +
                    " where id = ?");
            ps.setString(1,user.getName());
            ps.setString(2,user.getFamily());
            ps.setInt(3,user.getAge());
            ps.setString(4,user.getNationalCode());
            ps.setObject(5,user.getBirthday());
            ps.setInt(6,user.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }

    }

    @Override
    public void delete(User user) {
        try {
            PreparedStatement ps = database.prepareStatement("delete from users where id=?");
            ps.setInt(1,user.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }
    }

    @Override
    public Optional<User> getById(Integer id) {
        User user = null;
        try {
            PreparedStatement ps = database.prepareStatement("select * from users where account_id = ?");
            ps.setInt(1,id);
            ResultSet output =ps.executeQuery();
            if (output.first()){
                user = new User(output.getInt("id"),output.getString("name"),
                        output.getString("family"),output.getString("national_code"),
                        output.getObject("birth_day", LocalDate.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            return Optional.of(user);
        }
    }

    public void save(User user, int id) {
        try {
            PreparedStatement ps = database.prepareStatement("insert  into users(id,name,family,age,national_code," +
                    "birth_day,account_id)" +
                    "values (null,?,?,?,?,?,?)");
            ps.setString(1,user.getName());
            ps.setString(2,user.getFamily());
            ps.setInt(3,user.getAge());
            ps.setString(4,user.getNationalCode());
            ps.setObject(5,user.getBirthday());
            ps.setInt(6,id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }
    }
}
