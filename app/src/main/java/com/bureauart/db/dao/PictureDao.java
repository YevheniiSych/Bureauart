package com.bureauart.db.dao;

import androidx.room.Dao;
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

    @Query("SELECT * FROM Picture where artistName = :artistName")
    Single<List<Picture>> getPictureByArtistName(String artistName);

    @Query("SELECT * FROM Picture where pictureId = :pictureId")
    Single<Picture> getPictureById(int pictureId);
}
