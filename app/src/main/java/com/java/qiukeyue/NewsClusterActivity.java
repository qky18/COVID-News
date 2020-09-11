package com.java.qiukeyue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.java.qiukeyue.adapter.HomePagerAdapter;
import com.java.qiukeyue.adapter.NewsFragmentAdapter;
import com.java.qiukeyue.bean.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewsClusterActivity extends AppCompatActivity {
    private List<String> category;
    private HomePagerAdapter homePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_cluster);
        category = new ArrayList<>();
        category.add("传播");
        category.add("抗体");
        category.add("易感人群");
        category.add("疫情");
        category.add("疫苗");
        category.add("病毒");
        category.add("药品");
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        homePagerAdapter.setCategory(category);

        initToolbar();
        initView();
    }

    private void initView() {
        // init Tab view
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        // bind: fragment -> viewPager -> tabLayout
        viewPager.setAdapter(homePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.news_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("新闻聚类结果");
    }
}
