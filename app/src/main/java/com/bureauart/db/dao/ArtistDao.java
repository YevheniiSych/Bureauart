package com.bureauart.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bureauart.model.Artist;

import java.util.List;

@Dao
public interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllArtists(List<Artist> artists);

    @Query("SELECT * FROM Artist")
    List<Artist> getAllArtists();

    @Query("SELECT * FROM Artist where name = :name")
    Artist getArtistByName(String name);
}
