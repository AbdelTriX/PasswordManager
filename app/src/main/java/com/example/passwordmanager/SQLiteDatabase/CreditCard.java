package com.example.passwordmanager.SQLiteDatabase;

public class CreditCard {
    private int id;
    private String title;
    private int cardNumber;
    private String type;
    private String cardHolder;
    private String expiry;
    private int cvc;
    private int pin;

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public CreditCard(int id, String title, int cardNumber, String type, String cardHolder, String expiry, int cvc, int pin) {
        this.id = id;
        this.title = title;
        this.cardNumber = cardNumber;
        this.type = type;
        this.cardHolder = cardHolder;
        this.expiry = expiry;
        this.cvc = cvc;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
