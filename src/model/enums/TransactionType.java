package model.enums;

import Exceptions.InvalidInputException;

public enum TransactionType {
    DEPOSIT(1),WITHDRAW(2);
    private Integer value;

    TransactionType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
    public static TransactionType getTransactionType(Integer value){
        for (TransactionType transactionType :
                values()) {
            if (transactionType.getValue().equals(value))
                return transactionType;
        }
        throw new InvalidInputException("invalid value");
    }
}
