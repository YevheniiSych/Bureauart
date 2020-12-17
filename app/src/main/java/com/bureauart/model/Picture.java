package com.bureauart.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Picture {
    @PrimaryKey(autoGenerate = true)
    private int pictureId;
    private String imageUrl;
    private String name;
    private String artistName;
    private int width;
    private int height;
    private int price;

    public Picture(String imageUrl, String name, String artistName, int width, int height, int price) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.artistName = artistName;
        this.width = width;
        this.height = height;
        this.price = price;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
