package com.example.passwordmanager.SQLiteDatabase;

public class Login {
    private int id;
    private String title;
    private String email;
    private String password;

    public Login(int id,String title, String email, String password) {
        this.id = id;
        this.title = title;
        this.email = email;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
