package com.java.qiukeyue.ui.kg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.java.qiukeyue.R;

public class KGFragment extends Fragment {

    private com.java.qiukeyue.ui.kg.KGViewModel kgViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kgViewModel =
                ViewModelProviders.of(this).get(com.java.qiukeyue.ui.kg.KGViewModel.class);
        View root = inflater.inflate(R.layout.fragment_kg, container, false);
        final TextView textView = root.findViewById(R.id.text_kg);
        kgViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
