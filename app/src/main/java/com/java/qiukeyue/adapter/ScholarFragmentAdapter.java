package com.java.qiukeyue.adapter;

import android.graphics.Color;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.java.qiukeyue.R;
import com.java.qiukeyue.bean.News;
import com.java.qiukeyue.bean.Researcher;

import java.text.DecimalFormat;
import java.util.List;

import lecho.lib.hellocharts.util.ChartUtils;

public class ScholarFragmentAdapter extends RecyclerView.Adapter<ScholarFragmentAdapter.ScholarInfoViewHolder> {
    public interface OnScholarSelectedListener{
        void onScholarSelected(Researcher researcher);
    }

    private List<Researcher> scholarList;
    private OnScholarSelectedListener mListener;

    public ScholarFragmentAdapter(List<Researcher> scholarList, OnScholarSelectedListener listener){
        this.scholarList = scholarList;
        this.mListener = listener;
    }

    public void setScholarList(List<Researcher> scholarList) {
        this.scholarList = scholarList;
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ScholarInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = inflater.inflate(R.layout.item_scholar, parent, false);
        return new ScholarInfoViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(ScholarInfoViewHolder holder, int position) {
        holder.bind(scholarList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return scholarList.size();
    }


    class ScholarInfoViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mName;
        TextView hIndex, activityIndex, pubIndex, socialIndex, citeIndex;
        TextView mPosition, mAffiliation;
        public ScholarInfoViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.scholar_image);
            mName = itemView.findViewById(R.id.scholar_name);
            hIndex = itemView.findViewById(R.id.scholar_h_index);
            activityIndex = itemView.findViewById(R.id.scholar_activity);
            pubIndex = itemView.findViewById(R.id.scholar_pubs);
            socialIndex = itemView.findViewById(R.id.scholar_sociability);
            citeIndex = itemView.findViewById(R.id.scholar_citations);
            mPosition = itemView.findViewById(R.id.scholar_position);
            mAffiliation = itemView.findViewById(R.id.scholar_affiliation);

            mPosition.setMaxLines(1);
            mPosition.setEllipsize(TextUtils.TruncateAt.END);
            mAffiliation.setMaxLines(2);
            mAffiliation.setEllipsize(TextUtils.TruncateAt.END);
        }

        public void bind(final Researcher researcher, final OnScholarSelectedListener listener) {
            DecimalFormat df = new DecimalFormat("#0.00");

            // load scholar info
            mName.setText(researcher.getName());

            Researcher.IndicesBean indicesBean = researcher.getIndices();
            hIndex.setText(String.valueOf(indicesBean.getHindex()));
            activityIndex.setText((indicesBean.getActivity() == 0) ? "0" : df.format(indicesBean.getActivity()));
            pubIndex.setText(String.valueOf(indicesBean.getPubs()));
            socialIndex.setText(indicesBean.getSociability() == 0 ? "0" : df.format(indicesBean.getSociability()));
            citeIndex.setText(String.valueOf(indicesBean.getCitations()));

            mPosition.setText(researcher.getProfile().getPosition());
            mAffiliation.setText(researcher.getProfile().getAffiliation());

            if(researcher.getAvatar() != null){
                mImg.setMinimumHeight(50);
                mImg.setMinimumWidth(50);
                Glide.with(mImg.getContext())
                        .load(researcher.getAvatar())
                        .into(mImg);
            }

            if(researcher.isIs_passedaway()){
                mName.setTextColor(Color.GRAY);
            }

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onScholarSelected(researcher);
                    }
                }
            });
        }
    }
}
