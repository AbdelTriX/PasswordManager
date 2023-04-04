package com.example.passwordmanager.SQLiteDatabase;

import java.sql.Timestamp;

public class Note extends Main{
    private int id;
    private String title;
    private String description;


    public Note(int id, String title, String description, Timestamp time) {
        super(time);
        this.id = id;
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
