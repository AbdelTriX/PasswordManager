package com.example.passwordmanager.SQLiteDatabase.HistoryPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.passwordmanager.R;
import com.example.passwordmanager.SQLiteDatabase.PASMAN_Database;

import java.util.ArrayList;

public class History_Password extends AppCompatActivity {

    PASMAN_Database db;
    ListView lv;
    ArrayList<Password> list;
    Adapter_HistoryPassword adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_password);

        //action bar add or remove
        getSupportActionBar().setTitle("Password History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int login_id = getIntent().getIntExtra("id",-1);

        db = new PASMAN_Database(this);
        lv = findViewById(R.id.history_list);
        list = db.getPasswordHistory(login_id);
        adapter = new Adapter_HistoryPassword(getApplicationContext(), list);
        lv.setAdapter(adapter);
    }
}
