package com.java.qiukeyue.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.java.qiukeyue.fragment.NewsCollectionFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Since this(???) is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class HomePagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> tabTitles;

    public HomePagerAdapter(@NonNull FragmentManager fm, ArrayList<String> category) {
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
        Fragment fragment = new NewsCollectionFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.size();
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
