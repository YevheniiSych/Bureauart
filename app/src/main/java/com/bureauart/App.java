package com.bureauart;

import android.app.Application;

import androidx.room.Room;

import com.bureauart.db.BureauartDB;

public class App extends Application {

    private static App instance;

    private BureauartDB database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, BureauartDB.class, "PhotoDatabase")
                .fallbackToDestructiveMigration()
                .build(); // создаем объект бд
    }

    public static App getInstance() {
        return instance;
    }

    public BureauartDB getDatabase() {
        return database;
    }
}
