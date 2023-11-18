package com.example.carrentalnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.res.Resources;

//import kotlinx.coroutines.InactiveNodeList;


public class HatchBack extends Fragment {

    FrameLayout swift,tiago,kwid,alto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hatch_back, container, false);
        swift = view.findViewById(R.id.car_swift);
        tiago = view.findViewById(R.id.car_tiago);
        kwid = view.findViewById(R.id.car_kwid);
        alto = view.findViewById(R.id.car_alto);

        swift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Iswift = getResources().getInteger(R.integer.swift_price);
                String Nswift = getResources().getString(R.string.name_swift);
                change(Iswift, Nswift);
            }
        });
        tiago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Itiago = getResources().getInteger(R.integer.tiago_price);
                String Ntiago = getResources().getString(R.string.name_tiago);
                change(Itiago, Ntiago);
            }
        });
        kwid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Ikwid = getResources().getInteger(R.integer.kwid_price);
                String Nkwid = getResources().getString(R.string.name_kwid);
                change(Ikwid, Nkwid);
            }
        });
        alto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Ialto = getResources().getInteger(R.integer.alto_price);
                String Nalto = getResources().getString(R.string.name_alto);
                change(Ialto, Nalto);
            }
        });


        return view;
    }

    private void change(int charge, String name) {
        Intent intent = new Intent(getContext(), tab_date_and_time.class);
        int mobile=0;
        if (getActivity() != null) {
            Intent intent1 = getActivity().getIntent();
            if (intent1 != null && intent1.hasExtra("mobile")) {
                mobile = intent1.getIntExtra("mobile",0);
                // Use the charge value as needed
            }
        }
        intent.putExtra("charge",charge);
        intent.putExtra("mobile", mobile);
        intent.putExtra("name",name);
        startActivity(intent);

    }

}