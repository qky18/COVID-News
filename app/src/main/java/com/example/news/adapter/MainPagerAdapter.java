package com.example.news.adapter;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.news.fragment.NewsCollectionFragment;

// Since this(???) is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private final int PAGE_SIZE = 10;
    private String[] tabTitles;


    public MainPagerAdapter(@NonNull FragmentManager fm, String[] tabTitles) {
        super(fm);
        this.tabTitles = tabTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position % tabTitles.length];
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new NewsCollectionFragment();
        //Bundle args = new Bundle();
        // Our object is just an integer :-P
        //args.putInt(NewsCollectionFragment.ARG_OBJECT, i + 1);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    //TODO: 10?
    public int getCount() {
        return tabTitles.length;
    }

    /*
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText("布局" + position);
            tv.setTextSize(30.0f);
            tv.setGravity(Gravity.CENTER);
            (container).addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((View) object);
        }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    */


}
