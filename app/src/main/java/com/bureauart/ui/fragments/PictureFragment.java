package com.bureauart.ui.fragments;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bureauart.App;
import com.bureauart.MainActivity;
import com.bureauart.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.bureauart.ui.fragments.ArtistDetailFragment.ARTIST_NAME;

public class PictureFragment extends Fragment {

    public static final String PICTURE_ID = "PICTURE_ID";

    private ImageView photoImg;
    private TextView nameTxt;
    private TextView artistNameTxt;
    private TextView sizeTxt;
    private TextView priceTxt;
    private Button buyBtn;
    private Button backBtn;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        photoImg = view.findViewById(R.id.pictureDetailPhoto);
        nameTxt = view.findViewById(R.id.pictureDetailName);
        artistNameTxt = view.findViewById(R.id.pictureDetailArtistName);
        sizeTxt = view.findViewById(R.id.pictureDetailSize);
        priceTxt = view.findViewById(R.id.pictureDetailPrice);
        buyBtn = view.findViewById(R.id.buttonBuy);
        backBtn = view.findViewById(R.id.buttonBack);
        backBtn.setOnClickListener(v -> getActivity().onBackPressed());
        artistNameTxt.setOnClickListener(v -> {
            if (getActivity() != null) {
                Bundle bundle = new Bundle();
                bundle.putString(ARTIST_NAME, artistNameTxt.getText().toString());
                ((MainActivity) getActivity()).navController.navigate(R.id.artistDetailFragment, bundle);
            }
        });

        init();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home && getActivity() != null) {
            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("CheckResult")
    private void init() {
        if (getArguments() != null) {
            int pictureId = getArguments().getInt(PICTURE_ID);
            App.getInstance().getDatabase().pictureDao().getPictureById(pictureId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(picture -> {
                        Picasso.get()
                                .load(picture.getImageUrl())
                                .placeholder(R.drawable.ic_placeholder)
                                .error(R.drawable.ic_placeholder)
                                .into(photoImg);
                        nameTxt.setText(picture.getName());
                        artistNameTxt.setPaintFlags(artistNameTxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                        artistNameTxt.setText(picture.getArtistName());
                        sizeTxt.setText(getString(R.string.picture_size_placeholder, picture.getWidth(), picture.getHeight()));
                        priceTxt.setText(getString(R.string.picture_price_placeholder, picture.getPrice()));
                        if (getActivity() != null) {
                            Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setTitle(picture.getName());
                        }
                    });
        }
    }
}