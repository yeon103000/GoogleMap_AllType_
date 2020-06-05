package com.example.busanapp.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.busanapp.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private ArrayList<ImageDBHelper> items = new ArrayList<>();

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder viewHolder, int position) {
        ImageDBHelper item = items.get(position);

        Glide.with(viewHolder.itemView.getContext()).load(item.getUrl()).into(viewHolder.ivPhoto);
        viewHolder.tvPlace.setText(item.getPlace());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<ImageDBHelper> items) {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvPlace;

        ViewHolder(View itemView) {
            super(itemView);

            ivPhoto = itemView.findViewById(R.id.ImageItem);
            tvPlace = itemView.findViewById(R.id.ImagePlace);
        }
    }
}