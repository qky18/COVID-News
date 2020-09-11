package com.java.qiukeyue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.java.qiukeyue.adapter.NewsFragmentAdapter;
import com.java.qiukeyue.bean.News;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NewsOfflineActivity extends AppCompatActivity implements
        NewsFragmentAdapter.OnNewsSelectedListener {
    private List<News> newsList;
    private NewsFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_searched);
        newsList = News.listAll(News.class);
        initToolbar();
        initRecyclerView();
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
        toolbar.setTitle("新闻查看记录");
    }

    private void initRecyclerView() {
        // Set layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // Set adapter for recyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new NewsFragmentAdapter(newsList, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onNewsSelected(News news) {
        // Go to the details page for the selected news
        Intent intent = new Intent(this, NewsViewActivity.class);
        intent.putExtra("title", news.getTitle());
        intent.putExtra("content", news.getContent());
        startActivity(intent);
    }
}
