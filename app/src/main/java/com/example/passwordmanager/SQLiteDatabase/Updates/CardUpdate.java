package com.example.passwordmanager.SQLiteDatabase.Updates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordmanager.Accueil;
import com.example.passwordmanager.R;
import com.example.passwordmanager.SQLiteDatabase.PASMAN_Database;
import com.example.passwordmanager.Select_CreditCard;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CardUpdate extends AppCompatActivity {

    TextInputEditText titleEt, cardNumberEt, typeEt, cardHolderEt, monthEt, yearEt,  cvcEt, pinEt;
    TextView update;
    PASMAN_Database pasmanDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_update);

        titleEt = findViewById(R.id.titleEt);
        cardNumberEt = findViewById(R.id.cardNumberEt);
        typeEt = findViewById(R.id.typeEt);
        cardHolderEt = findViewById(R.id.cardHolderEt);
        monthEt = findViewById(R.id.monthEt);
        yearEt = findViewById(R.id.yearEt);
        cvcEt = findViewById(R.id.cvcEt);
        pinEt = findViewById(R.id.pinEt);

        update = findViewById(R.id.update);


        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        // Because cardNumber is Integer and should convert it
        long cardNumberV = intent.getLongExtra("cardNumber", -1);
        String cardNumber = String.valueOf(cardNumberV);


        String type = intent.getStringExtra("type");
        String cardHolder = intent.getStringExtra("cardHolder");

        // Same here we should convert
        String month = String.valueOf(getIntent().getIntExtra("month", -1));
        String year = String.valueOf(getIntent().getIntExtra("year", -1));
        String cvc = String.valueOf(getIntent().getIntExtra("cvc", -1));
        String pin = String.valueOf(getIntent().getIntExtra("pin", -1));

        titleEt.setText(title);
        cardNumberEt.setText(cardNumber);
        typeEt.setText(type);
        cardHolderEt.setText(cardHolder);
        monthEt.setText(month);
        yearEt.setText(year);
        cvcEt.setText(cvc);
        pinEt.setText(pin);
        //action bar add or remove
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get current year
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        pasmanDatabase = new PASMAN_Database(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values from EditTexts
                String titleUp = String.valueOf(titleEt.getText());
                String cardNumberStr = String.valueOf(cardNumberEt.getText());
                String typeUp = String.valueOf(typeEt.getText());
                String cardHolderUp = String.valueOf(cardHolderEt.getText()).toUpperCase();
                String monthStr = String.valueOf(monthEt.getText());
                String yearStr = String.valueOf(yearEt.getText());
                String cvcStr = String.valueOf(cvcEt.getText());
                String pinStr = String.valueOf(pinEt.getText());




                // Validate input values
                if (titleUp.isEmpty() | cardNumberStr.isEmpty() | typeUp.isEmpty() | cardHolderUp.isEmpty() | monthStr.isEmpty() | yearStr.isEmpty() | cvcStr.isEmpty() | pinStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all info", Toast.LENGTH_SHORT).show();
                } else if (cardNumberStr.length() != 16) {
                    Toast.makeText(getApplicationContext(), "Invalid length Card Number", Toast.LENGTH_SHORT).show();
                } else if (cvcStr.length() != 3) {
                    Toast.makeText(getApplicationContext(), "Invalid CVC (3).", Toast.LENGTH_SHORT).show();
                } else if (pinStr.length() != 4) {
                    Toast.makeText(getApplicationContext(), "Invalid PIN (4).", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(monthStr) < 1 | Integer.parseInt(monthStr) > 12) {
                    Toast.makeText(getApplicationContext(), "Invalid month.", Toast.LENGTH_SHORT).show();
                } else if (yearStr.length() != 4 | Integer.parseInt(yearStr) < 1970 | Integer.parseInt(yearStr) > currentYear + 14) {
                    Toast.makeText(getApplicationContext(), "Invalid year", Toast.LENGTH_SHORT).show();
                } else if (title.equals(title) && cardNumberStr.equals(cardNumber) && typeUp.equals(type) && cardHolderUp.equals(cardHolder) && monthStr.equals(month) && yearStr.equals(year) && cvcStr.equals(cvc) && pinStr.equals(pin)){
                    Toast.makeText(CardUpdate.this, "There's no update!!", Toast.LENGTH_SHORT).show();
                }else {
                    long cardNumber = Long.parseLong(cardNumberStr);
                    int month = Integer.parseInt(monthStr);
                    int year = Integer.parseInt(yearStr);
                    int cvc = Integer.parseInt(cvcStr);
                    int pin = Integer.parseInt(pinStr);

                    int id = getIntent().getIntExtra("id",-1);
                    String idStr = String.valueOf(id);

                    String result = pasmanDatabase.updateCreditCard(idStr, titleUp, cardNumber, type, cardHolderUp, month, year, cvc, pin);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                    if (result.equals("Update Successfully")) {
                        Intent intent = new Intent(getApplicationContext(), Accueil.class);
                        startActivity(intent);
                        finish();
                    }
                }

                // Save data to database

            }
        });

    }
}