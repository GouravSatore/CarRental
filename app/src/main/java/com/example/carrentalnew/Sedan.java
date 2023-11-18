package com.example.carrentalnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class Sedan extends Fragment {

    FrameLayout swift_dzire,city,verna,rapid;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sedan, container, false);
        swift_dzire = view.findViewById(R.id.car_swift_dzire);
        city = view.findViewById(R.id.car_city);
        verna = view.findViewById(R.id.car_verna);
        rapid = view.findViewById(R.id.car_rapid);

        swift_dzire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Iswift_dzire = getResources().getInteger(R.integer.swift_dzire_price);
                String Nswift_dzire = getResources().getString(R.string.name_swift_dzire);
                change(Iswift_dzire, Nswift_dzire);
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Icity = getResources().getInteger(R.integer.city_price);
                String Ncity = getResources().getString(R.string.name_city);
                change(Icity, Ncity);
            }
        });
        verna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Iverna = getResources().getInteger(R.integer.verna_price);
                String Nverna = getResources().getString(R.string.name_verna);
                change(Iverna, Nverna);
            }
        });
        rapid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Irapid = getResources().getInteger(R.integer.rapid_price);
                String Nrapid = getResources().getString(R.string.name_rapid);
                change(Irapid, Nrapid);
            }
        });
        return view;
    }
    private void change(int charge, String name) {
        Intent intent = new Intent(getContext(), tab_date_and_time.class);
        int mobile = 0;
        if (getActivity() != null) {
            Intent intent1 = getActivity().getIntent();
            if (intent1 != null && intent1.hasExtra("mobile")) {
                mobile = intent1.getIntExtra("mobile",0);
                // Use the charge value as needed
            }
        }
        intent.putExtra("name",name);
        intent.putExtra("mobile", mobile);
        intent.putExtra("charge",charge);
        startActivity(intent);
    }
}