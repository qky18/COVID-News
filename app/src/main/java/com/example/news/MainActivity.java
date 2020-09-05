package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.news.adapter.MainPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private String[] tabTitles = new String[]{"News", "Paper"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        if(tabTitles.length > 4){
            tabLayout.setTabMode (TabLayout.MODE_SCROLLABLE);
        }

        // bind: fragment -> viewPager -> tabLayout
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(),  tabTitles));
        //将TabLayout与ViewPager绑定
        tabLayout.setupWithViewPager(viewPager);
    }

}
