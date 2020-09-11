package com.java.qiukeyue.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.java.qiukeyue.R;
import com.java.qiukeyue.bean.News;
import com.orm.SugarContext;

import java.util.List;

public class NewsFragmentAdapter extends RecyclerView.Adapter<NewsFragmentAdapter.NewsInfoViewHolder> {
    public interface OnNewsSelectedListener{
        void onNewsSelected(News news);
    }

    private List<News> newsList;
    private OnNewsSelectedListener mListener;

    public NewsFragmentAdapter(List<News> newsList, OnNewsSelectedListener listener){
        this.newsList = newsList;
        this.mListener = listener;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
        this.notifyDataSetChanged();
    }

    public void addNewsList(List<News> news) {
        this.newsList.addAll(news);
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NewsInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = inflater.inflate(R.layout.item_news, parent, false);
        return new NewsInfoViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(NewsInfoViewHolder holder, int position) {
        holder.bind(newsList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
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

        public void bind(final News news, final OnNewsSelectedListener listener) {// load news info
            mTitle.setText(news.getTitle());
            mSource.setText(news.getSource());
            mDate.setText(news.getTime());
            if(news.getVisited()){
                mTitle.setTextColor(itemView.getResources().getColor(R.color.catArrangeHintColor));
            }

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onNewsSelected(news);
                    }
                }
            });
        }
    }
}
