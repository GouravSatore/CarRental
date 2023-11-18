package com.example.carrentalnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class viewPagerAdapterTime extends FragmentPagerAdapter {
    public viewPagerAdapterTime(@NonNull FragmentManager fm1) {
        super(fm1);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new time_fragment();
        else
            return new date_fragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
            return "Time";
        else
            return "Date";

    }
}
