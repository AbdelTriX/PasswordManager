package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Select_CreditCard extends AppCompatActivity {

    TextInputEditText titleEt, cardNumberEt, typeEt, cardHolderEt, dateEt, cvcEt, pinEt;
    TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_credit_card);

        titleEt = findViewById(R.id.titleEt);
        cardNumberEt = findViewById(R.id.cardNumberEt);
        typeEt = findViewById(R.id.typeEt);
        cardHolderEt = findViewById(R.id.cardHolderEt);
        dateEt = findViewById(R.id.dateEt);
        cvcEt = findViewById(R.id.cvcEt);
        pinEt = findViewById(R.id.pinEt);
        

        //action bar add or remove
        getSupportActionBar().setTitle("Credit Card");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}