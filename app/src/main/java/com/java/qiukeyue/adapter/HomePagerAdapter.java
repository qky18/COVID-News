package com.java.qiukeyue.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.java.qiukeyue.fragment.NewsCollectionFragment;

import java.util.ArrayList;
import java.util.List;

// Since this(???) is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class HomePagerAdapter extends FragmentStatePagerAdapter {
    List<String> category = new ArrayList<>();
    List<String> delCategory = new ArrayList<>();

    public HomePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return category.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return new NewsCollectionFragment(category.get(i));
    }

    @Override
    public int getCount() {
        return category.size();
    }

    public void setCategory(List<String> s) {
        this.category = s;
        notifyDataSetChanged();
    }

    public void setDelCategory(List<String> s) {
        this.delCategory = s;
        notifyDataSetChanged();
    }

    public ArrayList<String> getCategory() {
        return new ArrayList<>(category);
    }

    public ArrayList<String> getDelCategory() {
        return new ArrayList<>(delCategory);
    }
}
