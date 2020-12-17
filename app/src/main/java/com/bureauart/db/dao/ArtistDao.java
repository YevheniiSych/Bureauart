package com.bureauart.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bureauart.model.Artist;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllArtists(List<Artist> artists);

    @Query("SELECT * FROM Artist")
    Single<List<Artist>> getAllArtists();

    @Query("SELECT * FROM Artist where name = :name")
    Single<Artist> getArtistByName(String name);
}
