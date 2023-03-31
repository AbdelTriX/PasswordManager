package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

public class Select_Login extends AppCompatActivity {

    TextInputEditText emailEt, passwordEt, titleEt;
    TextView save, generatePassword;
    private SharedPreferences preferences;


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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                titleEt.setText("");
                emailEt.setText("");
                passwordEt.setText("");

                Intent intent = new Intent(getApplicationContext(),New_Item_Activity.class);
                startActivity(intent);
                finish();
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
