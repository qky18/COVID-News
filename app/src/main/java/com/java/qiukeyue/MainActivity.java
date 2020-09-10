package com.java.qiukeyue;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.java.qiukeyue.bean.News;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private Observer observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSearchbar();
        initNavView();
        initObserver();
    }

    private void initObserver() {
        // back-end manager: for fetching news
        observer = new Observer<List<News>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("MainActivity","observer subscribed");
            }
            @Override
            public void onNext(List<News> news) {
                Log.e("MainActivity","getList");
                for(News n: news){
                    Log.e("MainActivity", n.getTitle());
                }
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
                Log.e("MainActivity","Complete");
            }
        };
        Log.e("MainActivity","Observer available");
        Manager.search_n(null, observer);
    }

    private void initSearchbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        SearchView mSearchView = findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // TODO: submit query text & go to next page
                Log.e("Debug", "TextSubmit : " + s);
                Manager.search_n(s, observer);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void initNavView() {
        // mid navigation
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


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
