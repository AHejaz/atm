package service;


import Exceptions.AccountNotFindException;
import Exceptions.InvalidPasswordException;
import Exceptions.NotFoundException;
import database.Database;
import file.CsvService;
import model.Account;
import model.Transaction;
import model.User;
import model.enums.AccountType;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;


public class AccountService /* implements Runnable */{

    private Database database = Database.getInstance();
    private CsvService csvService = new CsvService();

    public Optional<Account> findAccount(String cardNumber) {
        return database.getAccountList().stream().filter(account -> account.getCard().getCardNumber().equals(cardNumber)).findFirst();
    }

    public Account login(String cardNumber, String password) {
        Account account = this.findAccount(cardNumber).orElseThrow(() -> {
            throw new NotFoundException("account not found");
        });
        if (account.getCard().validation(password))
            return account;
        else
            throw new InvalidPasswordException();
    }

    public Account createAccount(User user, String password, Integer type) {
        AccountType accountType = AccountType.getAccountType(type);
        Account account = new Account(user, password, accountType);
        database.getAccountList().add(account);
        return account;
    }

    public List<Transaction> lastTenTransactions(String cardNumber) {
        List<Transaction> transactions = findAccount(cardNumber).orElseThrow().getTransactions();
        if (transactions.isEmpty())
            throw new NotFoundException("There is no transaction");
        Collections.reverse(transactions);
        transactions.subList(0, transactions.size() < 10 ? transactions.size() : 9);
        return transactions;
    }

    public void LastTenTransactionsToCsv(String cardNumber) throws IOException {
        List<Transaction> transactions = lastTenTransactions(cardNumber);
        String[] header = {"amount", "transactionType", "date"};
        List<String[]> records = new ArrayList<>();
        transactions.forEach(transaction -> {
            String[] item = {String.valueOf(transaction.getAmount()), String.valueOf(transaction.getTransactionType()), String.valueOf(transaction.getDate())};
            records.add(item);
        });
        csvService.writer(header,records,"transaction");


    }

    public void moneyTransfer(String cardNum1, String cardNum2, double amount) {
        findAccount(cardNum1).ifPresentOrElse(account -> {
            Account account2 = findAccount(cardNum2).orElseThrow();
            account.withDraw(amount);
            account2.deposit(amount);
        }, () -> {
            throw new NotFoundException("no account by this card number");
        });

    }

//    @Override
//    public void run() {
//        sendSms(findLastElement());
//    }
//
//    private void sendSms(Optional<Account> lastElement) {
//        lastElement.ifPresentOrElse(account -> sms(account),()->{throw new AccountNotFindException();});
//
//    }
//
//    private void sms(Account account) {
//        System.out.println("==== sms ===");
//        System.out.println(account.toString());
//    }
//
//    public Optional<Account> findLastElement(){
//        Long lastElement = database.getAccountList().stream().count();
//        return Optional.ofNullable(database.getAccountList().stream().toList().get(Integer.valueOf(String.valueOf(lastElement))));
//    }


}

