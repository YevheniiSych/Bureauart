package com.bureauart.db.dao;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bureauart.model.Picture;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PictureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllPictures(List<Picture> pictures);

    @Query("SELECT * FROM Picture")
    Single<List<Picture>> getAllPictures();

    @Query("SELECT * FROM Picture where artistName = :artistId")
    Single<List<Picture>> getPictureByArtistId(int artistId);
}
