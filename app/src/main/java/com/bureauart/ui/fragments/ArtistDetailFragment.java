package com.bureauart.ui.fragments;

import android.annotation.SuppressLint;
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

public class ArtistDetailFragment extends Fragment {

    public static String ARTIST_NAME = "ARTIST_NAME";

    private ImageView photo;
    private TextView name;
    private TextView info;
    private Button showWorksBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artist_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        photo = view.findViewById(R.id.artistDetailPhoto);
        name = view.findViewById(R.id.artistDetailName);
        info = view.findViewById(R.id.artistDetailInfo);
        showWorksBtn = view.findViewById(R.id.showArtistWorks);
        showWorksBtn.setOnClickListener(v -> {
            if (getActivity() != null) {
                Bundle bundle = new Bundle();
                bundle.putString(ARTIST_NAME, name.getText().toString());
                ((MainActivity) getActivity()).navController.navigate(R.id.artistPicturesFragment, bundle);
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
            String artistName = getArguments().getString(ARTIST_NAME);
            App.getInstance().getDatabase().artistDao().getArtistByName(artistName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(artist -> {
                        Picasso.get()
                                .load(artist.getPhotoUrl())
                                .placeholder(R.drawable.ic_placeholder)
                                .error(R.drawable.ic_placeholder)
                                .into(photo);
                        name.setText(artist.getName());
                        info.setText(artist.getInfo());
                        if (getActivity() != null) {
                            Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setTitle(artist.getName());
                        }
                    });
        }
    }

}