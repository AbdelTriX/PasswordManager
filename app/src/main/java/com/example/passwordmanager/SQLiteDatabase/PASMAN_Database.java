package com.example.passwordmanager.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

public class PASMAN_Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pasman.app";
    public static final int DATABASE_VERSION = 1;

    public PASMAN_Database(Context con) {
        super(con, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE login (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT, email TEXT, password TEXT, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("CREATE TABLE credit_card (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, cardNumber INTEGER, type TEXT, cardHolder TEXT, expiry TEXT, cvc INTEGER, pin INTEGER, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("CREATE TABLE note (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS login ");
        db.execSQL("DROP TABLE IF EXISTS credit_card ");
        db.execSQL("DROP TABLE IF EXISTS note ");

        onCreate(db);

    }


    public String insertLogin(String title, String email, String password) {
        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("email", email);
        values.put("password", password);
        long newRow = s.insert("login", null, values);


        if (newRow == -1)
            return "Something Wrong";
        else
            return "Insert Succesfully";
    }


    public String insertCreditCard(String title, int cardNumber, String type, String cardHolder, String expiry, int cvc, int pin) {

        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("cardNumber", cardNumber);
        values.put("type", type);
        values.put("cardHolder", cardHolder);
        values.put("expiry", expiry);
        values.put("cvc", cvc);
        values.put("pin", pin);

        long newRow = s.insert("credit_card", null, values);

        if (newRow == -1)
            return "Something Wrong";
        return "Insert Succesfully";

    }


    public String insertNote(String title, String description) {

        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("description", description);
        long newRow = s.insert("note", null, values);

        if (newRow == -1)
            return "Something Wrong";
        return "Insert Succesfully";
    }

    public ArrayList<Main> getAllData() {
        ArrayList<Main> arrayList = new ArrayList<>();
        SQLiteDatabase s = this.getReadableDatabase();

        // Query the login table
        Cursor cursorLogin = s.rawQuery("SELECT * FROM login", null);
        while (cursorLogin.moveToNext()) {
            Login login = new Login(cursorLogin.getInt(0),
                    cursorLogin.getString(1),
                    cursorLogin.getString(2),
                    cursorLogin.getString(3),
                    Timestamp.valueOf(cursorLogin.getString(4)));
            arrayList.add(login);
        }
        cursorLogin.close();

        // Query the credit_card table
        Cursor cursorCard = s.rawQuery("SELECT * FROM credit_card", null);
        while (cursorCard.moveToNext()) {
            CreditCard card = new CreditCard(cursorCard.getInt(0),
                    cursorCard.getString(1),
                    cursorCard.getInt(2),
                    cursorCard.getString(3),
                    cursorCard.getString(4),
                    cursorCard.getString(5),
                    cursorCard.getInt(6),
                    cursorCard.getInt(7),
                    Timestamp.valueOf(cursorCard.getString(8)));
            arrayList.add(card);
        }
        cursorCard.close();

        // Query the note table
        Cursor cursorNote = s.rawQuery("SELECT * FROM note", null);
        while (cursorNote.moveToNext()) {
            Note note = new Note(cursorNote.getInt(0),
                    cursorNote.getString(1),
                    cursorNote.getString(2),
                    Timestamp.valueOf(cursorNote.getString(3)));
            arrayList.add(note);
        }
        cursorNote.close();


        // Sort the arrayList in descending order based on the timestamp
        Collections.sort(arrayList, Collections.reverseOrder());

        return arrayList;
    }


    ///////////////////////////// For Display data From Login ///////////////////////////////////////

//    public ArrayList getLOGIN_Data () {
//        ArrayList arrayList = new ArrayList();
//        SQLiteDatabase s = this.getReadableDatabase();
//        Cursor cursor = s.rawQuery("SELECT * FROM login",null);
//
//
//        cursor.moveToFirst(); // Bda mn First element
//        while (cursor.isAfterLast() == false) {
//
//            arrayList.add(new Login(cursor.getInt(0),
//                    cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getString(3)));
//            cursor.moveToNext();
//        }
//
//        return arrayList;
//    }
//    ///////////////////////////////// For Display data from Credit Card ///////////////////////////////////////////
//
//    public ArrayList getCreditCard_Data()  {
//
//        ArrayList arrayList = new ArrayList();
//        SQLiteDatabase s = this.getReadableDatabase();
//        Cursor cursor = s.rawQuery("SELECT * FROM credit_card",null);
//
//
//        cursor.moveToFirst(); // Bda mn First element
//        while (cursor.isAfterLast() == false) {
//
//            arrayList.add(new CreditCard(cursor.getInt(0),
//                    cursor.getString(1),
//                    cursor.getInt(2),
//                    cursor.getString(3),
//                    cursor.getString(4),
//                    cursor.getString(5),
//                    cursor.getInt(6),
//                    cursor.getInt(7)));
//            cursor.moveToNext();
//        }
//        return arrayList;
//    }
//
//    ////////////////////////////////// For Display Data from Note ///////////////////////////////////////////////////////
//    public ArrayList getNOTE_Data () {
//
//        ArrayList arrayList = new ArrayList();
//        SQLiteDatabase s = this.getReadableDatabase();
//        Cursor cursor = s.rawQuery("SELECT * FROM note",null);
//
//
//        cursor.moveToFirst(); // Bda mn First element
//        while (cursor.isAfterLast() == false) {
//
//            arrayList.add(new Note(cursor.getInt(0),
//                    cursor.getString(1),
//                    cursor.getString(2)));
//            cursor.moveToNext();
//        }
//        return arrayList;
//    }


        ///////////////////////// For reset /////////////////////////////////
        public void resetTables () {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS card");
            db.execSQL("CREATE TABLE credit_card (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, cardNumber INTEGER, Type TEXT, cardHolder TEXT, expiry TEXT, cvc INTEGER, pin INTEGER )");

            db.execSQL("DROP TABLE IF EXISTS credit_card");
            db.execSQL("CREATE TABLE credit_card (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, cardNumber INTEGER, type TEXT, cardHolder TEXT, expiry TEXT, cvc INTEGER, pin INTEGER, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

            db.execSQL("DROP TABLE IF EXISTS note");
            db.execSQL("CREATE TABLE note (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");


            db.close();
        }
    }
