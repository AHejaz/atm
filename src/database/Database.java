package database;

import model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {

    private static Database instance;


    private List<Account> accounts;

    private Database() {
        accounts = new ArrayList<>();
//        accounts.add(new Account(new User("Raha","Fayyaz","00227738" , DateFunctions.stringToDate("15-03-1998"))
//        ,"12345","54321"));
    }

    public static Database getInstance(){
        if (Objects.isNull(instance))
            return new Database();
        return instance;
    }



    //register a new account by user




    //getters
    public List<Account> getAccountList() {
        return accounts;
    }


    //get last 10 transactions


    @Override
    public String toString() {
        return "database.Database{" +
                "accounts=" + accounts +
                '}';
    }
}
