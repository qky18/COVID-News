package com.java.qiukeyue.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.java.qiukeyue.NewsOfflineActivity;
import com.java.qiukeyue.R;
import com.java.qiukeyue.bean.News;

public class UserFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        LinearLayout linearLayout1 = root.findViewById(R.id.layout_history);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewsOfflineActivity.class));
            }
        });

        LinearLayout linearLayout2 = root.findViewById(R.id.layout_delete_history);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News.deleteAll(News.class);
                Toast.makeText(getContext(), "历史新闻列表已清空", Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }
}
