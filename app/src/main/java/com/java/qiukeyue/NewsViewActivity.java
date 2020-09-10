package com.java.qiukeyue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class NewsViewActivity extends AppCompatActivity {
    private String title, content, source, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");

        initToolbar();
        initView();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.news_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsViewActivity.this.finish();
            }
        });
    }

    private void initView() {
        TextView mTitle = findViewById(R.id.text_title);
        TextView mContent = findViewById(R.id.text_content);

        mTitle.setText(title);
        mContent.setText(content);
    }

}
