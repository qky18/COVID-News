package com.java.qiukeyue;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.java.qiukeyue.adapter.EntityRelationAdapter;
import com.java.qiukeyue.adapter.NewsFragmentAdapter;
import com.java.qiukeyue.bean.Entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EntityViewActivity extends AppCompatActivity {
    private Entity entity;
    private EntityRelationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_view);

        // TODO: fix serializable
        entity = (Entity) getIntent().getSerializableExtra("entity");

        initToolbar();
        initView();
        initRecyclerView();
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

    private void initRecyclerView() {

        // Set adapter for recyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view_relation);
        mAdapter = new EntityRelationAdapter(entity.getAbstractInfo().getCOVID().getRelations());
        recyclerView.setAdapter(mAdapter);
    }

    private void initView() {
        TextView mLabel = findViewById(R.id.entity_label);
        TextView mInfo = findViewById(R.id.entity_info);
        ImageView mImg = findViewById(R.id.entity_img);

        mLabel.setText(entity.getLabel());
        mInfo.setText(entity.getLabel());
        Glide.with(mImg.getContext())
                .load(entity.getImg())
                .into(mImg);

        TextView mDef = findViewById(R.id.entity_definition_content);
        TextView mFeature = findViewById(R.id.entity_feature_content);
        TextView mInclude = findViewById(R.id.entity_include_content);
        TextView mCondition = findViewById(R.id.entity_condition_content);
        TextView mSpread = findViewById(R.id.entity_spread_content);
        TextView mApplication = findViewById(R.id.entity_application_content);
        Entity.AbstractInfoBean.COVIDBean.PropertiesBean prop = entity.getAbstractInfo().getCOVID().getProperties();
        mDef.setText(prop.getDefinition());
        mFeature.setText(prop.getFeature());
        mInclude.setText(prop.getInclude());
        mCondition.setText(prop.getCondition());
        mSpread.setText(prop.getSpread());
        mApplication.setText(prop.getApplication());

    }

}
