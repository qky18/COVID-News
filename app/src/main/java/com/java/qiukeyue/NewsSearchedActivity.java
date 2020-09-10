package com.java.qiukeyue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.java.qiukeyue.adapter.NewsFragmentAdapter;
import com.java.qiukeyue.bean.News;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NewsSearchedActivity extends AppCompatActivity implements
        NewsFragmentAdapter.OnNewsSelectedListener {
    private String keyword;
    private List<News> newsList = new LinkedList<>();
    private NewsFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_searched);
        keyword = getIntent().getStringExtra("keyword");
        Log.e("SearchActivity", keyword);
        initToolbar();
        initRecyclerView();
        initObserver(true);
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
        toolbar.setTitle("新闻搜索结果");
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

    private void initObserver(boolean getNew) {
        Observer<List<News>> observer = new Observer<List<News>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("NewsCollectionFrag", "observer subscribed");
            }

            @Override
            public void onNext(List<News> news) {
                Log.e("NewsSearched", "getList");
                mAdapter.setNewsList(news);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                Log.e("NewsCollectionFrag", "Complete");
            }
        };
        Log.e("NewsCollection","Observer available");
        Manager.search_n(keyword, observer);
    }

    @Override
    public void onNewsSelected(News news) {
        // Record clicked news
        news.save();
        List<News> n = News.listAll(News.class);
        for(News single : n){
            Log.e("Selected",single.getTitle());
        }

        // Go to the details page for the selected patient
        Intent intent = new Intent(this, NewsViewActivity.class);
        intent.putExtra("title", news.getTitle());
        intent.putExtra("content", news.getContent());
        startActivity(intent);
    }
}
