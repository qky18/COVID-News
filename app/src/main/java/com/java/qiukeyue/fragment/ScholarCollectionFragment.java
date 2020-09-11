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

import com.java.qiukeyue.Manager;
import com.java.qiukeyue.R;
import com.java.qiukeyue.ScholarViewActivity;
import com.java.qiukeyue.adapter.ScholarFragmentAdapter;
import com.java.qiukeyue.bean.Researcher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

// Instances of HomeFragment class are fragments representing a single type of collection
public class ScholarCollectionFragment extends Fragment implements
        ScholarFragmentAdapter.OnScholarSelectedListener {
    private final String TAG;
    private Observer<List<Researcher>> observer = null;
    private ScholarFragmentAdapter mAdapter;

    public ScholarCollectionFragment(final String tag){
        this.TAG = tag;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scholar_specific, container, false);

        initRecyclerView(rootView);
        initObserver();

        return rootView;
    }

    private void initRecyclerView(View rootView) {

        // Set layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // Set adapter for recyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ScholarFragmentAdapter(new ArrayList<Researcher>(), this);
        recyclerView.setAdapter(mAdapter);
    }

    private void initObserver() {
        observer = new Observer<List<Researcher>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG,"observer subscribed");
            }

            @Override
            public void onNext(List<Researcher> researchers) {
                mAdapter.setScholarList(researchers);
            }

            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
                Log.e(TAG,"Complete");
            }
        };

        Manager.getResearcher(observer, TAG.equals("追忆学者"));
    }


    @Override
    public void onScholarSelected(Researcher researcher) {
        // Go to the details page for the selected news
        Intent intent = new Intent(getActivity(), ScholarViewActivity.class);
        intent.putExtra("scholar", researcher);
        startActivity(intent);
    }
}