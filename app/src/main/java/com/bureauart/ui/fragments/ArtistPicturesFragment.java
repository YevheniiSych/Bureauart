package com.bureauart.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bureauart.App;
import com.bureauart.MainActivity;
import com.bureauart.R;
import com.bureauart.ui.adapter.PictureAdapter;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.bureauart.ui.fragments.ArtistDetailFragment.ARTIST_NAME;

public class ArtistPicturesFragment extends Fragment {

    private RecyclerView picturesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artist_pictures, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        picturesList = view.findViewById(R.id.artistPicturesList);
        picturesList.setLayoutManager(new GridLayoutManager(getContext(), 2));

        init();
    }

    @SuppressLint("CheckResult")
    private void init() {
        if (getArguments() != null) {
            String artistName = getArguments().getString(ARTIST_NAME);
            if (getActivity() != null) {
                Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setTitle(artistName);
            }
                    App.getInstance().getDatabase().pictureDao().getPictureByArtistName(artistName)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(pictures -> picturesList.setAdapter(new PictureAdapter(pictures, getActivity())));
        }
    }
}