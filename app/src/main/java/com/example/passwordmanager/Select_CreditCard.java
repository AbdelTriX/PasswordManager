package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordmanager.SQLiteDatabase.PASMAN_Database;
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

        save = findViewById(R.id.save);



        PASMAN_Database pasmanDatabase = new PASMAN_Database(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = String.valueOf(titleEt.getText());
                int cardNumber = Integer.parseInt(String.valueOf(cardNumberEt.getText()));
                String type = String.valueOf(typeEt.getText());
                String cardHolder = String.valueOf(cardHolderEt.getText());
                String date = String.valueOf(dateEt.getText());
                int cvc = Integer.parseInt(String.valueOf(cvcEt.getText()));
                int pin = Integer.parseInt(String.valueOf(pinEt.getText()));



                String result = pasmanDatabase.insertCreditCard(title,cardNumber,type,cardHolder,date,cvc,pin);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                if (result.equals("Insert Succesfully")) {
                    Intent intent = new Intent(getApplicationContext(), Accueil.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        //action bar add or remove
        getSupportActionBar().setTitle("Credit Card");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}