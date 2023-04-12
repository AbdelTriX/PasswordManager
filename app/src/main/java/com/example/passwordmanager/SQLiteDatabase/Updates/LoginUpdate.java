package com.example.passwordmanager.SQLiteDatabase.Updates;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordmanager.Accueil;
import com.example.passwordmanager.Generate_password;
import com.example.passwordmanager.R;
import com.example.passwordmanager.SQLiteDatabase.HistoryPassword.History_Password;
import com.example.passwordmanager.SQLiteDatabase.PASMAN_Database;
import com.example.passwordmanager.Select_Login;
import com.google.android.material.textfield.TextInputEditText;

public class LoginUpdate extends AppCompatActivity {

    TextInputEditText emailEt, passwordEt, titleEt;
    TextView update, generatePassword,passwordH;
    PASMAN_Database pasmanDatabase;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_update);

        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);
        titleEt = findViewById(R.id.titleEt);
        update = findViewById(R.id.update);
        passwordH = findViewById(R.id.passwordH);


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
        int id = getIntent().getIntExtra("id", -1);

        //action bar add or remove
        getSupportActionBar().setTitle(String.valueOf(title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Hide and Show Password in EditText
        passwordEt.setTransformationMethod(new PasswordTransformationMethod()); // Par default masqué
        passwordEt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
        passwordEt.setOnTouchListener((View v, @SuppressLint("ClickableViewAccessibility") MotionEvent event) -> {
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



        pasmanDatabase = new PASMAN_Database(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleUp = String.valueOf(titleEt.getText());
                String emailUp = String.valueOf(emailEt.getText());
                String passwordUp = String.valueOf(passwordEt.getText());

                // Because id is Integer, we should convert
                //int id = getIntent().getIntExtra("id", -1);
                String idString = String.valueOf(id);

                if (title.isEmpty() | email.isEmpty() | password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all info", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 7) {
                    Toast.makeText(getApplicationContext(), "Invalid password < 8", Toast.LENGTH_SHORT).show();
                } else if (email.length() < 10) {
                    Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();
                } else if (titleUp.equals(title) && emailUp.equals(email) && passwordUp.equals(password)) {
                    Toast.makeText(LoginUpdate.this, "There's no update !", Toast.LENGTH_SHORT).show();
                } else {
                    String result = pasmanDatabase.updateLogin(idString, titleUp, emailUp, passwordUp);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                    if (result.equals("Update Successfully")) {
                        // ADD TO HISTORY PASSWORD
                        pasmanDatabase.addPasswordHistory(passwordUp, id);

                        Intent intent = new Intent(getApplicationContext(), Accueil.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        passwordH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),History_Password.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

}
