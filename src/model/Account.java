package model;

import Exceptions.InvalidAmountException;
import model.enums.AccountType;
import model.enums.TransactionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Account {
    private Integer id;
    private final static Double MIN_BALANCE = 100d;
    private User user;
    private String accountNumber;
    private String password;
    private Double balance;
    private AccountType type;
    private Card card;
    private List<Transaction> transactions;


    public Account(User user, String password, AccountType type) {
        Random random = new Random();
        this.id = random.nextInt(100);
        this.user = user;
        this.accountNumber = String.valueOf(random.nextLong(10000000, 999999999));
        this.password = password;
        this.balance = 50d;
        this.type = type;
        this.card = new Card(password);
    }

    public Account(Integer id, User user, String accountNumber, String password, Double balance,
                   AccountType type, Card card, List<Transaction> transactions) {
        this.id = id;
        this.user = user;
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
        this.type = type;
        this.card = card;
        this.transactions = transactions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    public static Double getMinBalance() {
        return MIN_BALANCE;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                "name=" + user.getName() + " " + user.getFamily() +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                "}\n";
    }


    public void deposit(Double amount) {
        this.balance += amount;
        Transaction transaction = new Transaction(amount, TransactionType.DEPOSIT);
        if (Objects.isNull(transactions))
            transactions = new ArrayList<>();
        transactions.add(transaction);
    }

    public void withDraw(Double amount) {
        if (amount < this.balance) {
            this.balance = this.balance - amount;
            Transaction transaction = new Transaction(amount, TransactionType.WITHDRAW);
            if (Objects.isNull(transactions))
                transactions = new ArrayList<>();
            transactions.add(transaction);
        } else
            throw new InvalidAmountException();
    }


}

