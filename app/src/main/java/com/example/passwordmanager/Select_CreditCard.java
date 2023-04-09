package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordmanager.SQLiteDatabase.PASMAN_Database;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class Select_CreditCard extends AppCompatActivity {

    TextInputEditText titleEt, cardNumberEt, typeEt, cardHolderEt, monthEt,yearEt, cvcEt, pinEt;
    TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_credit_card);

        titleEt = findViewById(R.id.titleEt);
        cardNumberEt = findViewById(R.id.cardNumberEt);
        typeEt = findViewById(R.id.typeEt);
        cardHolderEt = findViewById(R.id.cardHolderEt);
        monthEt = findViewById(R.id.monthEt);
        yearEt = findViewById(R.id.yearEt);
        cvcEt = findViewById(R.id.cvcEt);
        pinEt = findViewById(R.id.pinEt);

        save = findViewById(R.id.save);


        // Get current year
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        PASMAN_Database pasmanDatabase = new PASMAN_Database(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values from EditTexts
                String title = String.valueOf(titleEt.getText()).toUpperCase();
                String cardNumberStr = String.valueOf(cardNumberEt.getText());
                String type = String.valueOf(typeEt.getText());

                // Make complet name like "Tricha ABDELMOUHIT or Oulouark OUSSAMA
                String[] cardHolderBefore = String.valueOf(cardHolderEt.getText()).split(" ");
                String cardHolder = cardHolderBefore[0].substring(0,1).toUpperCase() + cardHolderBefore[0].substring(1).toLowerCase() +
                        " "+cardHolderBefore[1].toUpperCase();

                String monthStr = String.valueOf(monthEt.getText());
                String yearStr = String.valueOf(yearEt.getText());
                String cvcStr = String.valueOf(cvcEt.getText());
                String pinStr = String.valueOf(pinEt.getText());


                // Validate input values
                if (title.isEmpty() | cardNumberStr.isEmpty() | type.isEmpty() | cardHolder.isEmpty() | monthStr.isEmpty() | yearStr.isEmpty() | cvcStr.isEmpty() | pinStr.isEmpty()) {
                    Toast.makeText(Select_CreditCard.this, "Please enter all info", Toast.LENGTH_SHORT).show();
                } else if (cardNumberStr.length() != 16) {
                    Toast.makeText(Select_CreditCard.this, "Invalid length Card Number", Toast.LENGTH_SHORT).show();
                } else if (cvcStr.length() != 3) {
                    Toast.makeText(Select_CreditCard.this, "Invalid CVC (3).", Toast.LENGTH_SHORT).show();
                } else if (pinStr.length() != 4) {
                    Toast.makeText(Select_CreditCard.this, "Invalid PIN (4).", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(monthStr) < 1 | Integer.parseInt(monthStr) > 12) {
                    Toast.makeText(Select_CreditCard.this, "Invalid month.", Toast.LENGTH_SHORT).show();
                } else if (yearStr.length() != 4 | Integer.parseInt(yearStr) < 1970 | Integer.parseInt(yearStr) > currentYear + 14) {
                    Toast.makeText(Select_CreditCard.this, "Invalid year", Toast.LENGTH_SHORT).show();
                }else {
                    long cardNumber = Long.parseLong(cardNumberStr);
                    int month = Integer.parseInt(monthStr);
                    int year = Integer.parseInt(yearStr);
                    int cvc = Integer.parseInt(cvcStr);
                    int pin = Integer.parseInt(pinStr);

                    String result = pasmanDatabase.insertCreditCard(title, cardNumber, type, cardHolder, month, year, cvc, pin);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                    if (result.equals("Insert Succesfully")) {
                        Intent intent = new Intent(getApplicationContext(), Accueil.class);
                        startActivity(intent);
                        finish();
                    }
                }

                // Save data to database

            }
        });





        //action bar add or remove
        getSupportActionBar().setTitle("Credit Card");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}