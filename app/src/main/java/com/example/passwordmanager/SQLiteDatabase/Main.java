package com.example.passwordmanager.SQLiteDatabase;

import java.sql.Timestamp;

public class Main implements Comparable<Main>{

    private Timestamp time;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Main(Timestamp time) {
        this.time = time;
    }

    @Override
    public int compareTo(Main o) {
        Main another = o;
        if (this.time == another.time) {
            return 0;
        }else if (this.time.after(another.time)){
            return 1;
        }else{
            return -1;
        }
    }
}
