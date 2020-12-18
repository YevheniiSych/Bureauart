package com.bureauart.ui.holder;

import android.graphics.Paint;
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
import com.bureauart.model.Picture;
import com.squareup.picasso.Picasso;

import static com.bureauart.ui.fragments.PictureFragment.PICTURE_ID;

public class PictureViewHolder extends RecyclerView.ViewHolder {
    private final TextView pictureName;
    private final TextView artistName;
    private final ImageView pictureImg;
    private final FragmentActivity activity;
    private final LinearLayout pictureItem;

    public PictureViewHolder(@NonNull View itemView, FragmentActivity activity) {
        super(itemView);
        pictureImg = itemView.findViewById(R.id.pictureItemImg);
        pictureName = itemView.findViewById(R.id.pictureItemNameTxt);
        artistName = itemView.findViewById(R.id.pictureItemArtistTxt);
        pictureItem = itemView.findViewById(R.id.pictureItem);
        this.activity = activity;
    }

    public void bind(Picture picture) {
        pictureName.setText(picture.getName());
        pictureName.setTag(picture.getPictureId());
        artistName.setPaintFlags(artistName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        artistName.setText(picture.getArtistName());
        Picasso.get()
                .load(picture.getImageUrl())
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(pictureImg);
        pictureItem.setTag(picture.getPictureId());
        pictureItem.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt(PICTURE_ID, (int) v.getTag());
            ((MainActivity) activity).navController.navigate(R.id.pictureFragment, bundle);
        });
    }
}
