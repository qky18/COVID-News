package com.java.qiukeyue.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.java.qiukeyue.fragment.ChartFragment;
import com.java.qiukeyue.fragment.NewsCollectionFragment;

import java.util.ArrayList;

public class ChartPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> tabTitles;
    private ArrayList<ChartFragment> fragments;

    public ChartPagerAdapter(@NonNull FragmentManager fm, ArrayList<String> category) {
        super(fm);
        this.tabTitles = category;
        this.fragments = new ArrayList<>();
        for(String title: tabTitles){
            this.fragments.add(new ChartFragment(title));
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position % tabTitles.size());
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return tabTitles.size();
    }

}
