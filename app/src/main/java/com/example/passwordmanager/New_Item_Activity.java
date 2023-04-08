package com.example.passwordmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordmanager.SQLiteDatabase.HistoryPassword.History_Password;

public class    New_Item_Activity extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"Login", "Credit Card", "Note"};
    int image[] = {R.drawable.login, R.drawable.creditcard, R.drawable.note};
    TextView cancel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        getSupportActionBar().setTitle("Add New Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Accueil.class);
                startActivity(intent);
                finish();
            }
        });

        //action bar add or remove


        listView = findViewById(R.id.listview);

        // craet adapter class inside select page class

        MyAdaptor adaptor = new MyAdaptor(this, mTitle, image);
        listView.setAdapter(adaptor);

        //daba set item onclick to other pages
        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(New_Item_Activity.this, Select_Login.class);
                    startActivity(intent);

                }
                if (position == 1) {
                    Intent intent = new Intent(New_Item_Activity.this, Select_CreditCard.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(New_Item_Activity.this, Select_Note.class);
                    startActivity(intent);                }

            }
        });


    }

    class MyAdaptor extends ArrayAdapter<String> {
        Context context;
        String rTitle[];
        int rImgs[];

        MyAdaptor(Context c, String title[], int Imgs[]) {
            super(c, R.layout.row, R.id.TXTVIEW, title);
            this.context = c;
            this.rTitle = title;
            this.rImgs = Imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView mytitle = row.findViewById(R.id.TXTVIEW);

            images.setImageResource(rImgs[position]);
            mytitle.setText(rTitle[position]);


            return row;

        }
    }
}