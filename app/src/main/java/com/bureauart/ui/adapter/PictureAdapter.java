package com.bureauart.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bureauart.R;
import com.bureauart.model.Picture;
import com.bureauart.ui.holder.PictureViewHolder;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureViewHolder> {

    private List<Picture> pictures;
    private FragmentActivity activity;

    public PictureAdapter(List<Picture> pictures, FragmentActivity activity) {
        this.pictures = pictures;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.picture_item, parent, false);
        return new PictureViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        holder.bind(pictures.get(position));
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }
}
