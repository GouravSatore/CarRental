package com.example.carrentalnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class tab_date_and_time extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_date_and_time);

        tab = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewPager);

        viewPagerAdapterTime adapter = new viewPagerAdapterTime(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tab.setupWithViewPager(viewpager);
    }

}