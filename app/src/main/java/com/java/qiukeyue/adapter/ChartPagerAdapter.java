package com.java.qiukeyue.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.java.qiukeyue.fragment.ChartFragment;

import java.util.ArrayList;

public class ChartPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> tabTitles;

    public ChartPagerAdapter(@NonNull FragmentManager fm, ArrayList<String> category) {
        super(fm);
        this.tabTitles = category;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position % tabTitles.size());
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return new ChartFragment(tabTitles.get(i));
    }

    @Override
    public int getCount() {
        return tabTitles.size();
    }

}
