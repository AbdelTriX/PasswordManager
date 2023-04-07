package com.example.passwordmanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.passwordmanager.SQLiteDatabase.Main;
import com.example.passwordmanager.SQLiteDatabase.MyAdapter;
import com.example.passwordmanager.SQLiteDatabase.PASMAN_Database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Home newInstance(String param1, String param2) {
        Fragment_Home fragment = new Fragment_Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }






    MyAdapter adapter;
    ArrayList<Main> main;
    PASMAN_Database db;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        FloatingActionButton newItem;

        View view =  inflater.inflate(R.layout.fragment__home, container, false);
        actionBar.setTitle("Home");
        //getActivity().setTitle("name label");

        newItem = view.findViewById(R.id.newItem);
        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),New_Item_Activity.class);
                startActivity(intent);
            }
        });
        ///////////////////  For Display data /////////////////////////////////////////////

//        List<Login> loginList = db.getLOGIN_Data();
//        List<CreditCard> creditCardList = db.getCreditCard_Data();
//        List<Note> noteList = db.getNOTE_Data();
//        db.close();
//
//        // Combine all data into one list
//        mDataList = new ArrayList<>();
//        mDataList.addAll(loginList);
//        mDataList.addAll(creditCardList);
//        mDataList.addAll(noteList);
//        Collections.reverse(mDataList);




//
        // Create adapter and set it to list view
        db = new PASMAN_Database(requireContext());
        main = db.getAllData();

        adapter = new MyAdapter(getActivity(), main);
        listView = view.findViewById(R.id.listview);
        listView.setAdapter(adapter);

//////////////////////////////////////////////////////////////////////////////////////

        return view;
    }
}
