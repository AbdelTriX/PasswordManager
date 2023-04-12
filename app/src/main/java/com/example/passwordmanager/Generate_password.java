package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.security.SecureRandom;

public class Generate_password extends AppCompatActivity {

    Button generateButton;
    SeekBar passwordLengthSeekBar;
    TextView passwordLengthTextView, save;
    CheckBox includeNumbersCheckBox;
    CheckBox includeSymbolsCheckBox;
    TextInputEditText passwordEditText;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_password);

        generateButton = findViewById(R.id.btn_generate_password);
        passwordLengthSeekBar = findViewById(R.id.sb_password_length);
        passwordLengthTextView = findViewById(R.id.tv_password_length);
        includeNumbersCheckBox = findViewById(R.id.cb_include_numbers);
        includeSymbolsCheckBox = findViewById(R.id.cb_include_symbols);


        passwordEditText = findViewById(R.id.showPassword);

        ////////////////////// Pour Action Bar /////////////////////////////////////////////
        //action bar add or remove
        getSupportActionBar().setTitle("Generate Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /////////////////////////////////////////////////////////////////////////////////


        /////////////////////// Pour Copier passwoord ////////////////////////////
        passwordEditText.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP &&
                    event.getRawX() >= passwordEditText.getRight() - passwordEditText.getCompoundDrawables()[2].getBounds().width()) {
                ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("Password", passwordEditText.getText().toString()));
                Toast.makeText(getApplicationContext(), "Password copied to clipboard", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
        //////////////////////////////////////////////////////////

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePassword();
            }
        });


        SeekBar passwordLengthSeekBar = findViewById(R.id.sb_password_length);
        TextView passwordLengthTextView = findViewById(R.id.tv_password_length);

        ////////// Initialiser le SeekBar ////////////////////
        passwordLengthSeekBar.setProgress(8);

        passwordLengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                passwordLengthTextView.setText("Password Length: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


    ///////////////////////// Generate function ///////////////////////////
    private void generatePassword() {
        int passwordLength = passwordLengthSeekBar.getProgress();
        boolean includeNumbers = includeNumbersCheckBox.isChecked();
        boolean includeSymbols = includeSymbolsCheckBox.isChecked();

        String password = generateRandomPassword(passwordLength, includeNumbers, includeSymbols);

        passwordEditText.setText(password);
    }

    private String generateRandomPassword(int passwordLength, boolean includeNumbers, boolean includeSymbols) {
        String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()_-+=[{]}|;:,<.>/?";

        String generatedPassword = upperChars + lowerChars;

        if (includeNumbers) {
            generatedPassword += numbers;
        }

        if (includeSymbols) {
            generatedPassword += symbols;
        }

        SecureRandom random = new SecureRandom();
        StringBuilder passwordBuilder = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(generatedPassword.length());
            char randomChar = generatedPassword.charAt(randomIndex);
            passwordBuilder.append(randomChar);
        }

        return passwordBuilder.toString();
    }


    //  Fach clicki user 3la arrow li kain f action, Had method kat3ayat 3la finish() bach trje3 activity li qbal
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // User clicked back button on the action bar
                finish(); // Finish the current activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
