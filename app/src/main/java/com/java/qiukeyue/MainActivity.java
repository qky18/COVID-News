package com.java.qiukeyue;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private String[] tabTitles = new String[]{"News", "Paper"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSearchbar();
        initNavView();
      
        // back-end: fetch news

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FetchNews.fetch("news");
                    FetchNews.printNews();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                //Manager m = new Manager();
                //m.refresh("news");
            }
        }).start();

         */


    }

    private void initSearchbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        SearchView mSearchView = findViewById(R.id.search_view);
        SearchView.SearchAutoComplete mSearchAutoComplete = mSearchView.findViewById(R.id.search_src_text);

        //设置触发查询的最少字符数（默认2个字符才会触发查询）
        //mSearchAutoComplete.setThreshold(1);
        //mSearchView.setSuggestionsAdapter(new CursorAdapter(MainActivity.this, R.layout.item_layout, cursor, new String[]{"name"}, new int[]{R.id.text1}));

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
