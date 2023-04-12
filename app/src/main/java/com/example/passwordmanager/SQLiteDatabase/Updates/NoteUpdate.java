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
import com.example.passwordmanager.Select_Note;
import com.google.android.material.textfield.TextInputEditText;

public class NoteUpdate extends AppCompatActivity {

    TextInputEditText titleEt, descriptionEt;
    TextView update;
    PASMAN_Database pasmanDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_update);

        titleEt = findViewById(R.id.titleEt);
        descriptionEt = findViewById(R.id.descriptionEt);
        update = findViewById(R.id.update);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");

        titleEt.setText(title);
        descriptionEt.setText(description);

        //action bar add or remove
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        pasmanDatabase = new PASMAN_Database(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleUp = String.valueOf(titleEt.getText());
                String descriptionUp = String.valueOf(descriptionEt.getText());

                int id = getIntent().getIntExtra("id", -1);
                String idString = String.valueOf(id);

                if (title.isEmpty() | description.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all info", Toast.LENGTH_SHORT).show();
                } else if(titleUp.equals(title) && descriptionUp.equals(description)) {
                    Toast.makeText(NoteUpdate.this, "There is no update !", Toast.LENGTH_SHORT).show();
                }else {
                    String result = pasmanDatabase.updateNote(idString, titleUp, descriptionUp);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                    if (result.equals("Update Successfully")) {
                        Intent intent = new Intent(getApplicationContext(), Accueil.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}