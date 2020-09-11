package com.java.qiukeyue;

import android.graphics.Color;
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
import com.java.qiukeyue.bean.Researcher;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import lecho.lib.hellocharts.util.ChartUtils;

public class ScholarViewActivity extends AppCompatActivity {
    private Researcher researcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholar_view);
        researcher = (Researcher) getIntent().getSerializableExtra("scholar");

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
                ScholarViewActivity.this.finish();
            }
        });
        toolbar.setTitle("学者详情");
    }

    private void initView() {
        // init widgets from view
        ImageView mImg;
        TextView mName;
        TextView hIndex, activityIndex, pubIndex, socialIndex, citeIndex;
        TextView mPosition, mAffiliation;
        TextView mBio, mEdu;

        mImg = findViewById(R.id.scholar_image);
        mName = findViewById(R.id.scholar_name);
        hIndex = findViewById(R.id.scholar_h_index);
        activityIndex = findViewById(R.id.scholar_activity);
        pubIndex = findViewById(R.id.scholar_pubs);
        socialIndex = findViewById(R.id.scholar_sociability);
        citeIndex = findViewById(R.id.scholar_citations);
        mPosition = findViewById(R.id.scholar_position);
        mAffiliation = findViewById(R.id.scholar_affiliation);
        mBio = findViewById(R.id.scholar_bio);
        mEdu = findViewById(R.id.scholar_edu);

        DecimalFormat df = new DecimalFormat("#0.00");

        // load scholar info
        mName.setText(researcher.getName());

        Researcher.IndicesBean indicesBean = researcher.getIndices();
        hIndex.setText(String.valueOf(indicesBean.getHindex()));
        activityIndex.setText((indicesBean.getActivity() == 0) ? "0" : df.format(indicesBean.getActivity()));
        pubIndex.setText(String.valueOf(indicesBean.getPubs()));
        socialIndex.setText(indicesBean.getSociability() == 0 ? "0" : df.format(indicesBean.getSociability()));
        citeIndex.setText(String.valueOf(indicesBean.getCitations()));

        mPosition.setText(researcher.getProfile().getPosition());
        mAffiliation.setText(researcher.getProfile().getAffiliation());
        mBio.setText(researcher.getProfile().getBio());
        mEdu.setText(researcher.getProfile().getEdu());

        if(researcher.getAvatar() != null){
            mImg.setMinimumHeight(50);
            mImg.setMinimumWidth(50);
            Glide.with(mImg.getContext())
                    .load(researcher.getAvatar())
                    .into(mImg);
        }


        if(researcher.isIs_passedaway()){
            mName.setTextColor(Color.GRAY);
        }

    }

}
