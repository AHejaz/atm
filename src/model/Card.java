package model;

import java.time.LocalDate;
import java.util.Random;

public class Card {
    private Integer id;
    private String cardNumber;
    private String password;
    private String cvv2;
    private LocalDate expireDate;
    private static final  String PRE_FIX = "50470610";

    public Card(String password) {
        Random random = new Random();
        this.cardNumber = PRE_FIX + random.nextInt(10000000,99999999);
        this.cvv2 = String.valueOf(random.nextInt(1000,9999));
        this.expireDate = LocalDate.now().plusYears(5);
        this.password = password;
    }

    public Card(Integer id, String cardNumber, String password, String cvv2, LocalDate expireDate) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.password = password;
        this.cvv2 = cvv2;
        this.expireDate = expireDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public boolean validation (String password){
        return password.equals(this.password);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", password='" + password + '\'' +
                ", cvv2='" + cvv2 + '\'' +
                ", expireDate=" + expireDate +
                '}';
    }
}
