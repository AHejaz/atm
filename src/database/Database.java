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

    public static Connection getInstance() throws SQLException {
        if (Objects.isNull(instance))
            return DriverManager.getConnection(DatabaseConfigLoader.getMessage("url"),
                    DatabaseConfigLoader.getMessage("user"),DatabaseConfigLoader.getMessage("password"));
        return instance;
    }

    public static void close() throws SQLException {
            instance.close();
    }

}
