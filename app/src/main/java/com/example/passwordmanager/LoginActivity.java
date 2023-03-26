package com.example.passwordmanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();;

        TextInputEditText passwordEditText = findViewById(R.id.passwordEt);
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


    }
}