package com.java.qiukeyue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class NewsViewActivity extends AppCompatActivity {
    private String title, content, source, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        initView();
    }

    private void initView() {
        TextView mTitle = findViewById(R.id.text_title);
        TextView mContent = findViewById(R.id.text_content);

        mTitle.setText(title);
        mContent.setText(content);
    }

}
