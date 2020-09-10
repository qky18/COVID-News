package com.java.qiukeyue.adapter;

import android.view.MotionEvent;

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
        assert tabTitles.size() > 0;
        this.fragments.add(new ChartFragment(tabTitles.get(0)));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position % tabTitles.size());
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        /*
        while(fragments.size() <= i){
            fragments.add(new ChartFragment(tabTitles.get(fragments.size())));
        }

         */
        return fragments.get(0);
    }

    @Override
    public int getCount() {
        return 1;

        //return tabTitles.size();
    }

}
