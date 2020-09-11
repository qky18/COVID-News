package com.java.qiukeyue.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.java.qiukeyue.CategoryActivity;
import com.java.qiukeyue.R;
import com.java.qiukeyue.adapter.HomePagerAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<String> category;
    private ArrayList<String> delCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = new ArrayList<>();
        delCategory = new ArrayList<>();
        category.add("news");
        category.add("paper");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        // init Tab view
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        if(category.size() > 4){
            tabLayout.setTabMode (TabLayout.MODE_SCROLLABLE);
        }

        // bind: fragment -> viewPager -> tabLayout
        assert getFragmentManager() != null;
        viewPager.setAdapter(new HomePagerAdapter(getFragmentManager(), category));
        tabLayout.setupWithViewPager(viewPager);

        // init drag layout
        ImageView addTab = view.findViewById(R.id.ic_add);
        addTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category", category);
                intent.putExtra("delCategory", delCategory);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }
}
