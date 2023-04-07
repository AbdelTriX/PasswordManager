package com.example.passwordmanager.SQLiteDatabase.Updates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordmanager.Accueil;
import com.example.passwordmanager.Generate_password;
import com.example.passwordmanager.R;
import com.example.passwordmanager.SQLiteDatabase.PASMAN_Database;
import com.example.passwordmanager.Select_Login;
import com.google.android.material.textfield.TextInputEditText;

public class LoginUpdate extends AppCompatActivity {

    TextInputEditText emailEt, passwordEt, titleEt;
    TextView update, generatePassword;
    PASMAN_Database pasmanDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_update);

        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);
        titleEt = findViewById(R.id.titleEt);
        update = findViewById(R.id.update);
        generatePassword = findViewById(R.id.generatePasswordDialog);


        generatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Generate_password.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");


        titleEt.setText(title);
        emailEt.setText(email);
        passwordEt.setText(password);

        //action bar add or remove
        getSupportActionBar().setTitle(String.valueOf(title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        pasmanDatabase = new PASMAN_Database(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleUp = String.valueOf(titleEt.getText());
                String emailUp = String.valueOf(emailEt.getText());
                String passwordUp = String.valueOf(passwordEt.getText());

                // Because id is Integer, we should convert
                int id = getIntent().getIntExtra("id", -1);
                String idString = String.valueOf(id);

                String result = pasmanDatabase.updateLogin(String.valueOf(idString),titleUp,emailUp,passwordUp);
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