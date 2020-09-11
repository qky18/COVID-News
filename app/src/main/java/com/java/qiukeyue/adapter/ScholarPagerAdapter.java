package com.java.qiukeyue.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.java.qiukeyue.fragment.ScholarCollectionFragment;

import java.util.List;

public class ScholarPagerAdapter extends FragmentStatePagerAdapter {
    final List<String> category;

    public ScholarPagerAdapter(@NonNull FragmentManager fm, List<String> category) {
        super(fm);
        this.category = category;
        for(String s: category){
            Log.e("ScholarPagerAdapter", s);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return category.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return new ScholarCollectionFragment(category.get(i));
    }

    @Override
    public int getCount() {
        return category.size();
    }
}
