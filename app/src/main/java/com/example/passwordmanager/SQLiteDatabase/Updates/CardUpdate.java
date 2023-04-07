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
import com.google.android.material.textfield.TextInputEditText;

public class CardUpdate extends AppCompatActivity {

    TextInputEditText titleEt, cardNumberEt, typeEt, cardHolderEt, dateEt, cvcEt, pinEt;
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
        dateEt = findViewById(R.id.dateEt);
        cvcEt = findViewById(R.id.cvcEt);
        pinEt = findViewById(R.id.pinEt);

        update = findViewById(R.id.update);


        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        // Because cardNumber is Integer and should convert it
        int cardNumberV = intent.getIntExtra("cardNumber",-1);
        String cardNumber = String.valueOf(cardNumberV);


        String type = intent.getStringExtra("type");
        String cardHolder = intent.getStringExtra("cardHolder");
        String expiry = intent.getStringExtra("expiry");

        // Same here we should convert
        String cvc = String.valueOf(getIntent().getIntExtra("cvc",-1));
        String pin = String.valueOf(getIntent().getIntExtra("pin",-1));

        titleEt.setText(title);
        cardNumberEt.setText(cardNumber);
        typeEt.setText(type);
        cardHolderEt.setText(cardHolder);
        dateEt.setText(expiry);
        cvcEt.setText(cvc);
        pinEt.setText(pin);
        //action bar add or remove
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        pasmanDatabase = new PASMAN_Database(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleUp = String.valueOf(titleEt.getText());
                int cardNumberUp = Integer.parseInt(String.valueOf(cardNumberEt.getText()));
                String typeUp = String.valueOf(typeEt.getText());
                String cardHolderUp = String.valueOf(cardHolderEt.getText());
                String expiryUp = String.valueOf(dateEt.getText());
                int cvcUp = Integer.parseInt(String.valueOf(cvcEt.getText()));
                int pinUp = Integer.parseInt(String.valueOf(pinEt.getText()));

                int id = getIntent().getIntExtra("id",-1);
                String idStr = String.valueOf(id);

                String result = pasmanDatabase.updateCreditCard(idStr,titleUp,cardNumberUp,typeUp,cardHolderUp,expiryUp,cvcUp,pinUp);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                if (result.equals("Update Successfully")){
                    Intent intent = new Intent(getApplicationContext(), Accueil.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}