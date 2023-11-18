package com.example.carrentalnew;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class Suv extends Fragment {
    FrameLayout nexon,creta,harrier,xuv500;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suv, container, false);
        nexon = view.findViewById(R.id.car_nexon);
        creta = view.findViewById(R.id.car_creta);
        harrier = view.findViewById(R.id.car_harrier);
        xuv500 = view.findViewById(R.id.car_xuv500);

        nexon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Inexon = getResources().getInteger(R.integer.nexon_price);
                String Nnexon = getResources().getString(R.string.name_nexon);
                change(Inexon,Nnexon);
            }
        });
        creta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Icreta = getResources().getInteger(R.integer.creta_price);
                String Ncreta = getResources().getString(R.string.name_creta);
                change(Icreta, Ncreta);
            }
        });
        harrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Iharrier = getResources().getInteger(R.integer.harrier_price);
                String Nharrier = getResources().getString(R.string.name_harrier);
                change(Iharrier, Nharrier);
            }
        });
       xuv500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Ixuv500 = getResources().getInteger(R.integer.xuv500_price);
                String Nxuv500 = getResources().getString(R.string.name_xuv500);
                change(Ixuv500, Nxuv500);
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
        intent.putExtra("name",name);
        intent.putExtra("mobile", mobile);
        intent.putExtra("charge",charge);
        startActivity(intent);
    }
}