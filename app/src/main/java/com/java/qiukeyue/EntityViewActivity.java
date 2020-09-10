package com.java.qiukeyue;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.java.qiukeyue.bean.Entity;

import java.util.Objects;

public class EntityViewActivity extends AppCompatActivity {
    private Entity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_view);

        // TODO: fix serializable
        entity = (Entity) getIntent().getSerializableExtra("entity");

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
                EntityViewActivity.this.finish();
            }
        });
    }

    private void initView() {
        // TODO: init recycler view (or list?)
        TextView mLabel = findViewById(R.id.entity_label);
        TextView mInfo = findViewById(R.id.entity_info);
        ImageView mImg = findViewById(R.id.entity_img);

        mLabel.setText(entity.getLabel());
        mInfo.setText(entity.getLabel());
        Glide.with(mImg.getContext())
                .load(entity.getUrl())
                .into(mImg);
    }

}
