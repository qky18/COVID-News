package com.java.qiukeyue.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.java.qiukeyue.CategoryActivity;
import com.java.qiukeyue.MainActivity;
import com.java.qiukeyue.R;
import com.java.qiukeyue.adapter.HomePagerAdapter;
import com.java.qiukeyue.ui.scholar.ScholarViewModel;
import com.java.qiukeyue.utils.TabViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private TabViewModel tabViewModel;
    private HomePagerAdapter homePagerAdapter;

    private void initView(View view){
        // init Tab view
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        ViewPager viewPager = view.findViewById(R.id.view_pager);

        // bind: fragment -> viewPager -> tabLayout
        homePagerAdapter = new HomePagerAdapter(getFragmentManager());
        viewPager.setAdapter(homePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // init drag layout
        ImageView addTab = view.findViewById(R.id.ic_add);
        addTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).jumpCategory();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initView(root);
        tabViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(TabViewModel.class);

        tabViewModel.getCategory().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> s) {
                homePagerAdapter.setCategory(s);
            }
        });

        tabViewModel.getDelCategory().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> s) {
                homePagerAdapter.setDelCategory(s);
            }
        });
        return root;
    }
}
