package model;

import model.enums.TransactionType;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {

    private Integer id;

    private Double amount;

    private TransactionType transactionType;

    private Date date;



    public Transaction(Double amount, TransactionType transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.date = new Date();
    }

    public Transaction(Integer id, Double amount, TransactionType transactionType, Date date) {
        this.id = id;
        this.amount = amount;
        this.transactionType = transactionType;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", date=" + date +
                "}\n";
    }
}
