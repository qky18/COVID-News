package com.java.qiukeyue.ui.kg;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.java.qiukeyue.EntitySearchedActivity;
import com.java.qiukeyue.R;

public class KGFragment extends Fragment {
    private SearchView mSearchView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kg, container, false);
        mSearchView = root.findViewById(R.id.search_view);
        mSearchView.setIconifiedByDefault(false);
        View mView = mSearchView.findViewById(R.id.search_plate);
        mView.setBackgroundColor(Color.TRANSPARENT);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // submit query text & go to next page
                Log.e("Debug", "TextSubmit : " + s);
                Intent intent = new Intent(getActivity(), EntitySearchedActivity.class);
                intent.putExtra("keyword", s);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return root;
    }

}
