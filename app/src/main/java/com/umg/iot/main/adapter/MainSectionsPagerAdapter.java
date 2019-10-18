package com.umg.iot.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainSectionsPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;
    private String[] titles;

    public MainSectionsPagerAdapter(@NonNull FragmentManager fm, int behavior, Fragment[] fragments, String[] titles) {
        super(fm, behavior);
        this.fragments = fragments;
        this.titles = titles;
    }


    @Override
    public CharSequence getPageTitle(int position){
        return this.titles[position];

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.fragments[position];
    }

    @Override
    public int getCount() {
        return this.fragments.length;
    }
}
