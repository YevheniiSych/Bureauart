package com.bureauart.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bureauart.R;
import com.bureauart.model.Artist;
import com.bureauart.ui.holder.ArtistHolder;
import com.bureauart.ui.holder.PictureViewHolder;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistHolder> {

    private List<Artist> artists;
    private FragmentActivity activity;

    public ArtistAdapter(List<Artist> artists, FragmentActivity activity) {
        this.artists = artists;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_item, parent, false);
        return new ArtistHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistHolder holder, int position) {
        holder.bind(artists.get(position));
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }
}
