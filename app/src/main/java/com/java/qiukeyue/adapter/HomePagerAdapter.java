package com.java.qiukeyue.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.java.qiukeyue.fragment.NewsCollectionFragment;

// Since this(???) is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class HomePagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabTitles;


    public HomePagerAdapter(@NonNull FragmentManager fm, String[] tabTitles) {
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
