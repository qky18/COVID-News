package com.java.qiukeyue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.java.qiukeyue.adapter.EntityCollectionAdapter;
import com.java.qiukeyue.bean.Entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class EntitySearchedActivity extends AppCompatActivity implements
        EntityCollectionAdapter.OnEntitySelectedListener {
    private String keyword;
    private List<Entity> entityList = new LinkedList<>();
    private EntityCollectionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_searched);
        keyword = getIntent().getStringExtra("keyword");
        initToolbar();
        initRecyclerView();
        initObserver();
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
        toolbar.setTitle("实体搜索结果");
    }

    private void initRecyclerView() {

        // Set layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // Set adapter for recyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new EntityCollectionAdapter(entityList, this);
        recyclerView.setAdapter(mAdapter);
    }

    private void initObserver() {
        Observer<List<Entity>> observer = new Observer<List<Entity>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<Entity> entities) {
                mAdapter.setEntityList(entities);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
        Manager.getEntiity(observer, keyword);
    }

    @Override
    public void onEntitySelected(Entity entity) {
        // Go to the details page for the selected entity
        Intent intent = new Intent(this, EntityViewActivity.class);
        intent.putExtra("label", entity.getLabel());
        intent.putExtra("info", entity.getAbstractInfo().getBaidu());
        intent.putExtra("img", entity.getImg());
        intent.putExtra("relation", entity.getAbstractInfo().getCOVID().getArrayRelations());
        intent.putExtra("property", entity.getAbstractInfo().getCOVID().getProperties());
        startActivity(intent);
    }
}
