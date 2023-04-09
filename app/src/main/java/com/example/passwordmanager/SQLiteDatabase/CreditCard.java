package com.example.passwordmanager.SQLiteDatabase;

import java.sql.Timestamp;

public class CreditCard extends Main{
    private int id;
    private String title;
    private long cardNumber;
    private String type;
    private String cardHolder;
    private int month;
    private int year ;
    private int cvc;
    private int pin;

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public CreditCard(int id, String title, long cardNumber, String type, String cardHolder, int month, int year,  int cvc, int pin, Timestamp time) {
        super(time);
        this.id = id;
        this.title = title;
        this.cardNumber = cardNumber;
        this.type = type;
        this.cardHolder = cardHolder;
        this.month = month;
        this.year = year;
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

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
