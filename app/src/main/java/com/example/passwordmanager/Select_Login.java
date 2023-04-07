package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordmanager.SQLiteDatabase.HistoryPassword.History_Password;
import com.example.passwordmanager.SQLiteDatabase.PASMAN_Database;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Select_Login extends AppCompatActivity {

    TextInputEditText emailEt, passwordEt, titleEt;
    TextView save, generatePassword, passwordH;
    private SharedPreferences preferences;
    private PASMAN_Database pasmanDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login);

        preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);
        titleEt = findViewById(R.id.titleEt);
        save = findViewById(R.id.save);
        generatePassword = findViewById(R.id.generatePasswordDialog);
        passwordH = findViewById(R.id.passwordH);



        //action bar add or remove
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        generatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Generate_password.class);
                startActivity(intent);
            }
        });

        passwordH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), History_Password.class);
                startActivity(intent);
                finish();
            }
        });



        /////////////////////////// Insert Data in SQLite Database /////////////////////////////////////

        pasmanDatabase = new PASMAN_Database(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = String.valueOf(titleEt.getText());
                String email = String.valueOf(emailEt.getText());
                String password = String.valueOf(passwordEt.getText());

                String result = pasmanDatabase.insertLogin(title,email,password);
                Toast.makeText(Select_Login.this, result, Toast.LENGTH_SHORT).show();

                if (result.equals("Insert Succesfully")) {
                    titleEt.setText("");
                    emailEt.setText("");
                    passwordEt.setText("");

                    // ADD TO HISTORY PASSWORD
                    pasmanDatabase.addPasswordHistory(password);

                    Intent intent = new Intent(getApplicationContext(), Accueil.class);
                    startActivity(intent);
                    finish();

                }
                pasmanDatabase.resetAllTables();
            }
        });
    }


///////////////////////////// To save data from Select login (Life cycle)   /////////////////////////////////////////
    @Override
    protected void onResume() {
        super.onResume();
        String title = preferences.getString("title", "");
        String email = preferences.getString("email", "");


        titleEt.setText(title);
        emailEt.setText(email);
    }
    @Override
    protected void onPause() {
        super.onPause();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("title", titleEt.getText().toString());
            editor.putString("email", emailEt.getText().toString());
            editor.apply();
        }
    }
