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

import com.java.qiukeyue.MainActivity;
import com.java.qiukeyue.Manager;
import com.java.qiukeyue.NewsViewActivity;
import com.java.qiukeyue.R;
import com.java.qiukeyue.adapter.NewsFragmentAdapter;
import com.java.qiukeyue.bean.News;
import com.orm.SugarContext;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

// Instances of HomeFragment class are fragments representing a single type of collection
public class NewsCollectionFragment extends Fragment implements
        NewsFragmentAdapter.OnNewsSelectedListener {
    private final String TAG;
    private Observer<List<News>> observer = null;
    private List<News> newsList = new LinkedList<>();
    private NewsFragmentAdapter mAdapter;
    private RefreshLayout refreshLayout;
    boolean isLoadingMore = false;

    public NewsCollectionFragment(final String tag){
        this.TAG = tag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(TAG.equals("news")) {
            SugarContext.init(getContext());
        }
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
        refreshLayout = rootView.findViewById(R.id.swipe_refresh);
        if(TAG.equals("news") || TAG.equals("paper")) {
            refreshLayout.setRefreshHeader(new ClassicsHeader(this.getContext()));
            refreshLayout.setRefreshFooter(new ClassicsFooter(this.getContext()));
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    Log.e(TAG, "onRefresh: ");
                    // This method performs the actual data-refresh operation.
                    // The method calls setRefreshing(false) when it's finished.
                    isLoadingMore = false;
                    Manager.refresh_n(TAG, true, observer);
                }
            });
            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshlayout) {
                    isLoadingMore = true;
                    Manager.refresh_n(TAG, false, observer);
                }
            });
        }
        else {
            refreshLayout.setEnableRefresh(false);
            refreshLayout.setEnableLoadMore(false);
        }
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
    }

    private void initObserver(boolean getNew) {
        observer = new Observer<List<News>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG,"observer subscribed");
            }
            @Override
            public void onNext(List<News> news) {
                Log.e(TAG,"getList");
                if(TAG.equals("news")|| TAG.equals("paper")) {
                    if (refreshLayout != null) {
                        if (isLoadingMore) {
                            refreshLayout.finishLoadMore();
                            if (TAG.equals("news")) {
                                for (News n : news) {
                                    if (News.find(News.class, "_id = ?", n.get_id()).size() > 0) {
                                        n.setVisited(true);
                                    } else {
                                        n.setVisited(false);
                                    }
                                }
                            }
                            mAdapter.addNewsList(news);
                        } else {
                            refreshLayout.finishRefresh();
                            if (TAG.equals("news")) {
                                for (News n : news) {
                                    if (News.find(News.class, "_id = ?", n.get_id()).size() > 0) {
                                        n.setVisited(true);
                                    } else {
                                        n.setVisited(false);
                                    }
                                }
                            }

                            mAdapter.setNewsList(news);
                        }
                    }
                }
                else{
                    mAdapter.setNewsList(news);
                }
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
                Log.e(TAG,"Complete");
            }
        };
        if(TAG.equals("news") || TAG.equals("paper")) {
            Manager.refresh_n(TAG, getNew, observer);
        } else {
            Manager.getCluster(observer, TAG, getContext());
        }
    }

    @Override
    public void onNewsSelected(News news) {
        if(TAG.equals("news") && !news.getVisited()){
            news.save();
        }

        // Go to the details page for the selected news
        Intent intent = new Intent(getActivity(), NewsViewActivity.class);
        intent.putExtra("title", news.getTitle());
        intent.putExtra("content", news.getContent());
        intent.putExtra("visited", news.getVisited());
        intent.putExtra("source", news.getSource());
        intent.putExtra("date", news.getTime());
        startActivityForResult(intent, MainActivity.NEWS);
    }
}