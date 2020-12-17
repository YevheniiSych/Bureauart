package com.bureauart.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bureauart.db.dao.ArtistDao;
import com.bureauart.db.dao.PictureDao;
import com.bureauart.model.Artist;
import com.bureauart.model.Picture;

@Database(
        entities = {
                Picture.class,
                Artist.class
        },
        version = 1
)
abstract public class BureauartDB extends RoomDatabase {
    abstract public PictureDao pictureDao();

    abstract public ArtistDao artistDao();

}
