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
    private String label, info, img;
    private List<com.java.qiukeyue.bean.Entity.AbstractInfoBean.COVIDBean.RelationsBean> relation;
    private Entity.AbstractInfoBean.COVIDBean.PropertiesBean prop;
    private EntityRelationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_view);

        label = getIntent().getStringExtra("label");
        info = getIntent().getStringExtra("info");
        img = getIntent().getStringExtra("img");
        relation = (List<Entity.AbstractInfoBean.COVIDBean.RelationsBean>) getIntent().getSerializableExtra("relation");
        prop = (Entity.AbstractInfoBean.COVIDBean.PropertiesBean) getIntent().getSerializableExtra("property");

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
        mAdapter = new EntityRelationAdapter(relation);
        recyclerView.setAdapter(mAdapter);
    }

    private void initView() {
        TextView mLabel = findViewById(R.id.entity_label);
        TextView mInfo = findViewById(R.id.entity_info);
        ImageView mImg = findViewById(R.id.entity_img);

        mLabel.setText(label);
        mInfo.setText(info);
        Glide.with(mImg.getContext())
                .load(img)
                .into(mImg);

        TextView mDef = findViewById(R.id.entity_definition_content);
        TextView mFeature = findViewById(R.id.entity_feature_content);
        TextView mInclude = findViewById(R.id.entity_include_content);
        TextView mCondition = findViewById(R.id.entity_condition_content);
        TextView mSpread = findViewById(R.id.entity_spread_content);
        TextView mApplication = findViewById(R.id.entity_application_content);
        mDef.setText(prop.getDefinition());
        mFeature.setText(prop.getFeature());
        mInclude.setText(prop.getInclude());
        mCondition.setText(prop.getCondition());
        mSpread.setText(prop.getSpread());
        mApplication.setText(prop.getApplication());

    }

}
