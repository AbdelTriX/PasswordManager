package com.example.passwordmanager.SQLiteDatabase.HistoryPassword;

import android.content.Context;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.passwordmanager.R;

import java.util.ArrayList;

public class Adapter_HistoryPassword extends BaseAdapter {

    Context c ;
    ArrayList<Password> data;
    Adapter_HistoryPassword(Context c, ArrayList<Password> data){
        this.c = c;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater li = LayoutInflater.from(c);
        View root = li.inflate(R.layout.item_history_activity,null,false);
        TextView passwordTv = root.findViewById(R.id.passwordHistoryTv);

        // Set password text and hide the password
        Password password = data.get(i);
        passwordTv.setText(password.getPassword());



        // Hide and show the password
        passwordTv.setTransformationMethod(new PasswordTransformationMethod());
        passwordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show/Hide password
                if(passwordTv.getTransformationMethod() == null) {
                    passwordTv.setTransformationMethod(new PasswordTransformationMethod());
                    passwordTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
                } else {
                    passwordTv.setTransformationMethod(null);
                    passwordTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_no_visibility, 0);
                }
            }
        });


        return root;
    }

}
