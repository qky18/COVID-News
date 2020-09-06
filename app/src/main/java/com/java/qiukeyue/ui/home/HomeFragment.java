package com.java.qiukeyue.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.java.qiukeyue.R;
import com.java.qiukeyue.adapter.HomePagerAdapter;

public class HomeFragment extends Fragment {

    private String[] tabTitles = new String[]{"News", "Paper"};
    private HomeViewModel homeViewModel;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        // init Tab view
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        if(tabTitles.length > 4){
            tabLayout.setTabMode (TabLayout.MODE_SCROLLABLE);
        }

        // bind: fragment -> viewPager -> tabLayout
        assert getFragmentManager() != null;
        viewPager.setAdapter(new HomePagerAdapter(getFragmentManager(),  tabTitles));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;

        /*
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

         */
    }
}
