package com.bureauart.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bureauart.R;
import com.bureauart.model.Picture;
import com.squareup.picasso.Picasso;

public class PictureViewHolder extends RecyclerView.ViewHolder {
    private final TextView pictureName;
    private final TextView artistName;
    private final ImageView pictureImg;

    public PictureViewHolder(@NonNull View itemView) {
        super(itemView);
        pictureImg = itemView.findViewById(R.id.pictureItemImg);
        pictureName = itemView.findViewById(R.id.pictureItemNameTxt);
        artistName = itemView.findViewById(R.id.pictureItemArtistTxt);
    }

    public void bind(Picture picture){
        pictureName.setText(picture.getName());
        artistName.setText(picture.getArtistName());
        Picasso.get()
                .load(picture.getImageUrl())
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(pictureImg);
    }
}
