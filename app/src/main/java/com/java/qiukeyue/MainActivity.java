package com.java.qiukeyue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.java.qiukeyue.utils.TabViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEWS = 111, CATEGORY = 13;
    private TabViewModel tabViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabViewModel = ViewModelProviders.of(this).get(TabViewModel.class);
        initSearchbar();
        initNavView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("MainActivity", ""+requestCode);
        switch (requestCode){
            case NEWS:{
                Log.e("MainActivity", "news back");
                // refresh
                startActivity(new Intent(this, MainActivity.class));
                finish();
                overridePendingTransition(0, 0);
                break;
            }
            case CATEGORY:{
                Log.e("MainActivity", "cat back");
                List<String> cat = data.getStringArrayListExtra("category");
                tabViewModel = ViewModelProviders.of(this).get(TabViewModel.class);
                tabViewModel.setCategory(data.getStringArrayListExtra("category"));
                tabViewModel.setDelCategory(data.getStringArrayListExtra("delCategory"));
                for (String s: cat){
                    Log.e("MainActivity", s);
                }
                break;
            }
            default:
                Log.e("MainActivity", "default");
        }
    }

    public void jumpCategory(){
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        intent.putExtra("category", new ArrayList<>(tabViewModel.getCategory().getValue()));
        intent.putExtra("delCategory",  new ArrayList<>(tabViewModel.getDelCategory().getValue()));
        startActivityForResult(intent, MainActivity.CATEGORY);
    }

    private void initSearchbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        final SearchView mSearchView = findViewById(R.id.search_view);
        mSearchView.findViewById(R.id.search_plate).setBackground(null);
        mSearchView.findViewById(R.id.submit_area).setBackground(null);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // submit query text & go to next page
                Log.e("MainActivity", "TextSubmit : " + s);
                mSearchView.setIconified(true);
                Intent intent = new Intent(MainActivity.this, NewsSearchedActivity.class);
                intent.putExtra("keyword", s);
                startActivityForResult(intent, NEWS);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    private void initNavView() {
        // bottom navigation
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration fragAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_scholar,
                R.id.navigation_knowledge_graph, R.id.navigation_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, fragAppBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
