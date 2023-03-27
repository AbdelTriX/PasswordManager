package com.example.passwordmanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText passwordEditText;
    TextView signIn;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //////////////// Hide Navbar //////////////////
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();;
//////////////////////////////////////////////////////
        passwordEditText = findViewById(R.id.passwordEt);
        signIn = findViewById(R.id.signIn);

        ///////////////// FOR  HIDE AND SHOW PASSWORD ///////////////////////////////////////////////////
        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
        passwordEditText.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (passwordEditText.getRight() - passwordEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    int selectionStart = passwordEditText.getSelectionStart();
                    int selectionEnd = passwordEditText.getSelectionEnd();

                    if (passwordEditText.getTransformationMethod() instanceof PasswordTransformationMethod) {
                        // Show password
                        passwordEditText.setTransformationMethod(null);
                        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_no_visibility, 0);
                    } else {
                        // Hide password
                        passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
                        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
                    }

                    passwordEditText.setSelection(selectionStart, selectionEnd);
                    return true;
                }
            }
            return false;
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}