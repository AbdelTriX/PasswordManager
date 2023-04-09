package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordmanager.SQLiteDatabase.PASMAN_Database;
import com.google.android.material.textfield.TextInputEditText;

public class Select_Note extends AppCompatActivity {


    TextInputEditText titleEt, descriptionEt;
    TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_note);

        titleEt = findViewById(R.id.titleEt);
        descriptionEt = findViewById(R.id.descriptionEt);
        save = findViewById(R.id.save);


        PASMAN_Database pasmanDatabase = new PASMAN_Database(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = String.valueOf(titleEt.getText()).toUpperCase();
                String description = String.valueOf(descriptionEt.getText());


                if (title.isEmpty() | description.isEmpty() ) {
                    Toast.makeText(Select_Note.this, "Please enter all info", Toast.LENGTH_SHORT).show();
                }
                else {
                    String result = pasmanDatabase.insertNote(title, description);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                    if (result.equals("Insert Succesfully")) {
                        Intent intent = new Intent(getApplicationContext(), Accueil.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });


        //action bar add or remove
        getSupportActionBar().setTitle("Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}