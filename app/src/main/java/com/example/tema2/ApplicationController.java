package com.example.tema2;

import android.app.Application;
import android.provider.SyncStateContract;

import androidx.room.Room;

public class ApplicationController extends Application {

    private static ApplicationController mInstance;

    private static DB mAppDatabase;
    public static ApplicationController getInstance() {
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mAppDatabase = Room.databaseBuilder(getApplicationContext(), DB.class, "users").allowMainThreadQueries().build();
    }
    public static DB getAppDatabase(){
        return mAppDatabase;
    }
}
