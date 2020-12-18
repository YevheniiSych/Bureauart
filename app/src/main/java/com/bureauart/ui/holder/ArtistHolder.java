package com.bureauart.ui.holder;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bureauart.MainActivity;
import com.bureauart.R;
import com.bureauart.model.Artist;
import com.squareup.picasso.Picasso;

import static com.bureauart.ui.fragments.ArtistDetailFragment.ARTIST_NAME;

public class ArtistHolder extends RecyclerView.ViewHolder {

    private final ImageView artistPhoto;
    private final TextView artistName;
    private final LinearLayout artistItem;
    private final FragmentActivity activity;

    public ArtistHolder(@NonNull View itemView, FragmentActivity activity) {
        super(itemView);
        artistPhoto = itemView.findViewById(R.id.artistItemPhoto);
        artistName = itemView.findViewById(R.id.artistItemName);
        artistItem = itemView.findViewById(R.id.artistItem);
        this.activity = activity;
    }

    public void bind(Artist artist) {
        artistName.setText(artist.getName());
        Picasso.get()
                .load(artist.getPhotoUrl())
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(artistPhoto);
        artistItem.setTag(artist.getName());
        artistItem.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(ARTIST_NAME, v.getTag().toString());
            ((MainActivity) activity).navController.navigate(R.id.artistDetailFragment, bundle);
        });
    }
}