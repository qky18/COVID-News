package com.java.qiukeyue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.java.qiukeyue.adapter.MainPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private String[] tabTitles = new String[]{"News", "Paper"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FetchNews.fetch("news");
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                //Manager m = new Manager();
                //m.refresh("news");
            }
        }).start();
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
