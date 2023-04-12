package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.MotionEvent;
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
    TextView save, generatePassword;
    private SharedPreferences preferences;
    private PASMAN_Database pasmanDatabase;


    @SuppressLint("ClickableViewAccessibility")
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

            // Hide and Show Password in EditText
        passwordEt.setTransformationMethod(new PasswordTransformationMethod()); // Par default masqué
        passwordEt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
        passwordEt.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (passwordEt.getRight() - passwordEt.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    int selectionStart = passwordEt.getSelectionStart();
                    int selectionEnd = passwordEt.getSelectionEnd();

                    if (passwordEt.getTransformationMethod() instanceof PasswordTransformationMethod) {
                        // Show password
                        passwordEt.setTransformationMethod(null);
                        passwordEt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_no_visibility, 0);
                    } else {
                        // Hide password
                        passwordEt.setTransformationMethod(new PasswordTransformationMethod());
                        passwordEt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
                    }

                    passwordEt.setSelection(selectionStart, selectionEnd);
                    return true;
                }
            }
            return false;
        });

        /////////////////////////// Insert Data in SQLite Database /////////////////////////////////////

        pasmanDatabase = new PASMAN_Database(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = String.valueOf(titleEt.getText()).toUpperCase();
                String email = String.valueOf(emailEt.getText());
                String password = String.valueOf(passwordEt.getText());

                if (title.isEmpty() | email.isEmpty() | password.isEmpty()) {
                    Toast.makeText(Select_Login.this, "Please enter all info", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 7) {
                    Toast.makeText(Select_Login.this, "Invalid password < 8", Toast.LENGTH_SHORT).show();
                } else if (email.length() < 10) {
                    Toast.makeText(Select_Login.this, "Invalid email", Toast.LENGTH_SHORT).show();
                } else {
                    String result = pasmanDatabase.insertLogin(title, email, password);
                    Toast.makeText(Select_Login.this, result, Toast.LENGTH_SHORT).show();


                    titleEt.setText("");
                    emailEt.setText("");
                    passwordEt.setText("");

                    // ADD TO HISTORY PASSWORD
                    int id_login = pasmanDatabase.getMaxLoginId();
                    pasmanDatabase.addPasswordHistory(password, id_login);

                    Intent intent = new Intent(getApplicationContext(), Accueil.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }

    ///////////////////////////// To save data from Select login (Life cycle) (Fach katmchi password manager w katrje3)  /////////////////////////////////////////
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

