package com.java.qiukeyue.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.java.qiukeyue.R;

public class NewsFragmentAdapter extends RecyclerView.Adapter<NewsFragmentAdapter.NewsInfoViewHolder> {
    private final int NUM_SIZE = 10;

    @Override
    public NewsInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = inflater.inflate(R.layout.item_news, parent, false);
        NewsInfoViewHolder viewHolder = new NewsInfoViewHolder(childView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsInfoViewHolder holder, int position) {
    }

    @Override
    //TODO: 20?
    public int getItemCount() {
        return NUM_SIZE;
    }


    class NewsInfoViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        TextView mSource;
        TextView mDate;
        public NewsInfoViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.news_title);
            mSource = itemView.findViewById(R.id.news_source);
            mDate = itemView.findViewById(R.id.news_date);
        }
    }
}
