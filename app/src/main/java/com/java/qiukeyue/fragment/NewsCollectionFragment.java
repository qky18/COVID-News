package com.java.qiukeyue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.java.qiukeyue.MainActivity;
import com.java.qiukeyue.Manager;
import com.java.qiukeyue.NewsViewActivity;
import com.java.qiukeyue.R;
import com.java.qiukeyue.adapter.NewsFragmentAdapter;
import com.java.qiukeyue.bean.News;
import com.orm.SugarContext;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

// Instances of HomeFragment class are fragments representing a single type of collection
public class NewsCollectionFragment extends Fragment implements
        NewsFragmentAdapter.OnNewsSelectedListener {
    private Observer<List<News>> observer = null;
    private List<News> newsList = new LinkedList<>();
    private NewsFragmentAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    boolean isLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SugarContext.init(getContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        initRecyclerView(rootView);
        initObserver(true);
        initSwipeRefresh(rootView);

        return rootView;
    }

    private void initSwipeRefresh(View rootView) {
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("newsCollection", "onRefresh: ");
                // This method performs the actual data-refresh operation.
                // The method calls setRefreshing(false) when it's finished.
                Manager.refresh_n("news", true, observer);
            }
        });
    }

    private void initRecyclerView(View rootView) {

        // Set layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // Set adapter for recyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new NewsFragmentAdapter(newsList, this);
        recyclerView.setAdapter(mAdapter);

        // Set scroll listener
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("test", "onScrolled");

                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == mAdapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        Manager.refresh_n("news", false, observer);
                    }
                }
            }
        });
    }

    private void initObserver(boolean getNew) {
        observer = new Observer<List<News>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("MainActivity","observer subscribed");
            }
            @Override
            public void onNext(List<News> news) {
                Log.e("MainActivity","getList");
                if(swipeRefreshLayout != null){
                    swipeRefreshLayout.setRefreshing(false);
                }
                mAdapter.setNewsList(news);
                isLoading = false;
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
        Manager.refresh_n("news", getNew, observer);
    }

    @Override
    public void onNewsSelected(News news) {
        // Record clicked news
        news.save();
        List<News> n = News.listAll(News.class);
        for(News single : n){
            Log.e("Selected",single.getTitle());
        }


        // Go to the details page for the selected patient
        Intent intent = new Intent(getActivity(), NewsViewActivity.class);
        intent.putExtra("title", news.getTitle());
        intent.putExtra("content", news.getContent());
        startActivity(intent);
    }
}