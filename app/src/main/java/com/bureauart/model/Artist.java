package com.bureauart.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Artist {
    @PrimaryKey(autoGenerate = true)
    private int artistId;
    private String photoUrl;
    private String name;
    private String info;

    public Artist(String photoUrl, String name, String info) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.info = info;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
