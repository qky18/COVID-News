package com.java.qiukeyue.ui.scholar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.java.qiukeyue.MainActivity;
import com.java.qiukeyue.R;
import com.java.qiukeyue.adapter.HomePagerAdapter;
import com.java.qiukeyue.adapter.ScholarPagerAdapter;
import com.orzangleli.radar.XRadarView;

import java.util.ArrayList;
import java.util.Arrays;

public class ScholarFragment extends Fragment {
    private ScholarPagerAdapter scholarPagerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_scholar, container, false);
        initView(root);
        return root;
    }

    private void initView(View view){
        // init Tab view
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        ViewPager viewPager = view.findViewById(R.id.view_pager);

        // bind: fragment -> viewPager -> tabLayout
        String[] category = new String[]{"高关注学者", "追忆学者"};
        scholarPagerAdapter = new ScholarPagerAdapter(getFragmentManager(), new ArrayList<>(Arrays.asList(category)));
        viewPager.setAdapter(scholarPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
