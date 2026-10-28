package com.jomgmi.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.jomgmi.app.R;
import com.jomgmi.app.model.GalleryItem;

import java.util.List;

public class GalleryFeedAdapter extends RecyclerView.Adapter<GalleryFeedAdapter.ViewHolder> {

    public interface OnGalleryItemClickListener {
        void onGalleryItemClick(int position, GalleryItem item);
    }

    private final Context context;
    private final List<GalleryItem> items;
    private final OnGalleryItemClickListener listener;

    public GalleryFeedAdapter(Context context, List<GalleryItem> items, OnGalleryItemClickListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gallery_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GalleryItem item = items.get(position);

        if (item.getResId() != 0) {
            holder.ivThumb.setImageResource(item.getResId());
        } else {
            holder.ivThumb.setImageResource(R.drawable.ic_school);
        }

        holder.tvTag.setText(item.getCategory());
        holder.tvTitle.setText(item.getTitle());
        holder.tvDesc.setText(item.getDescription());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onGalleryItemClick(position, item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvTag;
        TextView tvTitle;
        TextView tvDesc;
        MaterialCardView cardItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumb = itemView.findViewById(R.id.ivGalleryCardThumb);
            tvTag = itemView.findViewById(R.id.tvGalleryCardTag);
            tvTitle = itemView.findViewById(R.id.tvGalleryCardTitle);
            tvDesc = itemView.findViewById(R.id.tvGalleryCardDesc);
            cardItem = itemView.findViewById(R.id.cardGalleryItem);
        }
    }
}
