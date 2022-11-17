package service;


import Exceptions.AgeException;
import Exceptions.InvalidInputException;
import dao.UserDao;
import database.Database;
import model.Account;
import model.User;
import util.Util;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Consumer;

public class UserService {

    private UserDao userDao = new UserDao();

    public User createUser(String fName, String lName, String nationalCode, LocalDate bDay) {
        final User[] user = {null};

        if (findAccountByNationalCode(nationalCode).isPresent()){
            throw new InvalidInputException("User already exist.");
        }

        if (bDay.getYear() < 18)
            throw new AgeException();
        user[0] = new User(fName, lName, nationalCode, bDay);
        Util.print(user[0]);
        return user[0];
    }

    public Optional<User> findAccountByNationalCode(String nationalCode) {
        for (User user : userDao.getAll()) {
            if (user.getNationalCode().equals(nationalCode))
                return Optional.of(user);
        }
        return Optional.empty();
    }
}