package com.example.passwordmanager.SQLiteDatabase.HistoryPassword;

import com.example.passwordmanager.SQLiteDatabase.Main;

import java.sql.Time;
import java.sql.Timestamp;

public class Password extends Main {
    private int id;
    private String password;
    Timestamp time;
    private int login_id;

    public Password(int id, String password, Timestamp time, int login_id) {
        super(time);
        this.id = id;
        this.password = password;
        this.login_id = login_id;
    }

    @Override
    public Timestamp getTime() {
        return time;
    }

    @Override
    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
