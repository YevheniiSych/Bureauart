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
}
