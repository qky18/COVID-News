package com.java.qiukeyue.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.java.qiukeyue.R;
import com.java.qiukeyue.bean.Entity;

import java.util.List;

public class EntityCollectionAdapter extends RecyclerView.Adapter<EntityCollectionAdapter.EntityInfoViewHolder> {

    public interface OnEntitySelectedListener {
        void onEntitySelected(Entity entity);
    }

    private List<Entity> entityList;
    private OnEntitySelectedListener mListener;

    public EntityCollectionAdapter(List<Entity> entityList, OnEntitySelectedListener listener){
        this.entityList = entityList;
        this.mListener = listener;
    }

    public void setEntityList(List<Entity> entities) {
        this.entityList = entities;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public EntityInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = inflater.inflate(R.layout.item_entity, parent, false);
        return new EntityInfoViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(EntityInfoViewHolder holder, int position) {
        holder.bind(entityList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }


    class EntityInfoViewHolder extends RecyclerView.ViewHolder {
        TextView mLabel;
        TextView mInfo;
        ImageView mImg;
        public EntityInfoViewHolder(View itemView) {
            super(itemView);
            mLabel = itemView.findViewById(R.id.entity_label);
            mInfo = itemView.findViewById(R.id.entity_info);
            mImg = itemView.findViewById(R.id.entity_img);

            mInfo.setMaxLines(2);
            mInfo.setEllipsize(TextUtils.TruncateAt.END);
            mImg.setMaxHeight(50);
            mImg.setMaxWidth(50);
        }

        public void bind(final Entity entity, final OnEntitySelectedListener listener) {

            // load entity info
            mLabel.setText(entity.getLabel());
            mInfo.setText(entity.getLabel());
            if(entity.getImg() != null){
                mImg.setMinimumHeight(50);
                mImg.setMinimumWidth(50);
                Glide.with(mImg.getContext())
                        .load(entity.getImg())
                        .into(mImg);
                Log.e("Entity Img", entity.getImg());
            }

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onEntitySelected(entity);
                    }
                }
            });

        }
    }
}
