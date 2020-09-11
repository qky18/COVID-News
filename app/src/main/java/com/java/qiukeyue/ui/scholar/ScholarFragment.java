package com.java.qiukeyue.ui.scholar;

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
import com.orzangleli.radar.XRadarView;

public class ScholarFragment extends Fragment {

    private ScholarViewModel scholarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_scholar, container, false);
        //XRadarView xRadarView = root.findViewById(R.id.xradar_view);
        //String[] titles = new String[]{"击杀","金钱","防御","魔法","物理","助攻","生存"};
        //double[] percents = new double[]{1.0, 0.46,0.63,0.75,0.5,0.9,0.26};

        //xRadarView.setTitles(titles);
        //xRadarView.setPercents(percents);

        /*
        final TextView textView = root.findViewById(R.id.text_scholar);
        scholarViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

         */


        return root;
    }
}
