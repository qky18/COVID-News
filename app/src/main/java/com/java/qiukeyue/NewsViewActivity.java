package com.java.qiukeyue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.java.qiukeyue.utils.ShareNews;

import java.util.Objects;

public class NewsViewActivity extends AppCompatActivity {
    private String title, content, source, date;
    private boolean visited;
    private PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        source = getIntent().getStringExtra("source");
        date = getIntent().getStringExtra("date");

        initToolbar();
        initView();
        initFloatingActionBar();
    }

    private void initFloatingActionBar() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });

        popupMenu = new PopupMenu(this, fab);
        Menu menu = popupMenu.getMenu();
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.share_menu, menu);
        popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.share_wx:
                        ShareNews.shareWeChatFriend(getApplicationContext(), title);
                        break;
                    case R.id.share_qq:
                        ShareNews.shareQQFriend(getApplicationContext(), title);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.news_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("NewsViewActivity", "back");
                finish();
            }
        });
    }

    private void initView() {
        TextView mTitle = findViewById(R.id.text_title);
        TextView mContent = findViewById(R.id.text_content);
        TextView mSource = findViewById(R.id.text_source);
        TextView mDate = findViewById(R.id.text_date);

        mTitle.setText(title);
        mContent.setText(content);
        mSource.setText(source);
        mDate.setText(date);
    }

}
