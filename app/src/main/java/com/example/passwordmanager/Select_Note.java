package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Select_Note extends AppCompatActivity {


    TextInputEditText titleEt, descriptionEt;
    TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_note);

        titleEt = findViewById(R.id.titleEt);
        save = findViewById(R.id.save);


        //action bar add or remove
        getSupportActionBar().setTitle("Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}