package com.example.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;

public class NewsFragmentAdapter extends RecyclerView.Adapter<NewsFragmentAdapter.NewsInfoViewHolder> {
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
        return 6;
    }


    class NewsInfoViewHolder extends RecyclerView.ViewHolder {

        ImageView mThumnail;
        TextView mSource;
        TextView mTitle;
        TextView mPreview;
        public NewsInfoViewHolder(View itemView) {
            super(itemView);
            mThumnail = itemView.findViewById(R.id.news_thumbnail);
            mSource = itemView.findViewById(R.id.news_source);
            mTitle = itemView.findViewById(R.id.news_title);
            mPreview = itemView.findViewById(R.id.news_preview);
        }
    }
}
