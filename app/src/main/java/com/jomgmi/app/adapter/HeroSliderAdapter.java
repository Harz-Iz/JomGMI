package com.jomgmi.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jomgmi.app.R;
import com.jomgmi.app.model.GalleryItem;

import java.util.List;

public class HeroSliderAdapter extends RecyclerView.Adapter<HeroSliderAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position, GalleryItem item);
    }

    private final Context context;
    private final List<GalleryItem> items;
    private final OnItemClickListener listener;

    public HeroSliderAdapter(Context context, List<GalleryItem> items, OnItemClickListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hero_slide, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GalleryItem item = items.get(position);
        if (item.getResId() != 0) {
            holder.ivHeroSlide.setImageResource(item.getResId());
        } else {
            holder.ivHeroSlide.setImageResource(R.drawable.ic_school);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position, item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHeroSlide;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHeroSlide = itemView.findViewById(R.id.ivHeroSlide);
        }
    }
}
