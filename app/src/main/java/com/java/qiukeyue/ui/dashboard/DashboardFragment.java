package com.java.qiukeyue.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.java.qiukeyue.CategoryActivity;
import com.java.qiukeyue.Manager;
import com.java.qiukeyue.R;
import com.java.qiukeyue.adapter.ChartPagerAdapter;
import com.java.qiukeyue.adapter.HomePagerAdapter;
import com.java.qiukeyue.bean.CovidData;
import com.java.qiukeyue.bean.News;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DashboardFragment extends Fragment {
    private ArrayList<String> category;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = new ArrayList<>();
        category.add("China");
        category.add("World");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        // init Tab view
        TabLayout tabLayout = root.findViewById(R.id.tab_layout);
        ViewPager viewPager = root.findViewById(R.id.view_pager);

        // bind: fragment -> viewPager -> tabLayout
        assert getFragmentManager() != null;
        viewPager.setAdapter(new ChartPagerAdapter(getFragmentManager(), category));
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }

}
