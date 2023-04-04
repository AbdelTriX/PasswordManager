package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.passwordmanager.SQLiteDatabase.PASMAN_Database;

public class Accueil extends AppCompatActivity {

    MeowBottomNavigation bottomNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        setTitle("Accueil Page");


        ////////////////////////////////////////////////////////////////////////////////
        bottomNav = findViewById(R.id.bottonNav);
        bottomNav.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNav.add(new MeowBottomNavigation.Model(2, R.drawable.ic_profile));
        /////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////// ADD BOTTOM NAVBAR ////////////////////////////////////////////////
        bottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment;
                if (item.getId() == 2) {
                    fragment = new Fragment_Profile();
                } else {
                    fragment = new Fragment_Home();
                }
                loadFragment(fragment);
            }
        });
        bottomNav.show(1, true);

        bottomNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
            }
        });
        bottomNav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_container, fragment, null)
                .commit();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
}