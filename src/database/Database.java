package database;

import file.DatabaseConfigLoader;
import model.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {

    private static Connection instance;


    private Database() {
    }

    public static Connection getInstance() {
        if (Objects.isNull(instance)) {
            try {
                return DriverManager.getConnection(DatabaseConfigLoader.getMessage("url"),
                        DatabaseConfigLoader.getMessage("user"),DatabaseConfigLoader.getMessage("password"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public static void close(){
        try {
            instance.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
