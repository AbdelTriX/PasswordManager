package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Select_CreditCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_credit_card);


        //action bar add or remove
        getSupportActionBar().setTitle("Credit Card");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}