package com.example.passwordmanager.SQLiteDatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.passwordmanager.R;
import com.example.passwordmanager.SQLiteDatabase.Login;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Login> mLoginList;

    public MyAdapter(Context context, ArrayList<Login> loginList) {
        mContext = context;
        mLoginList = loginList;
    }

    @Override
    public int getCount() {
        return mLoginList.size();
    }

    @Override
    public Object getItem(int position) {
        return mLoginList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_login, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleTv);
        TextView usernameTextView = convertView.findViewById(R.id.emailTv);
        TextView passwordTextView = convertView.findViewById(R.id.passwordTv);

        Login login = mLoginList.get(position);
        titleTextView.setText(login.getTitle());
        usernameTextView.setText(login.getEmail());
        passwordTextView.setText(login.getPassword());

        return convertView;
    }

}
