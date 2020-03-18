package com.example.tema2;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepo{

    private DB appDatabase;

    public UserRepo(Context context) {
        appDatabase = ApplicationController.getAppDatabase();
    }
    public void insertTask(final User user, final OnUserRepositoryActionListener listener) {
        new InsertTask(listener).execute(user);
    }
    public void deleteTask(final User user, final OnUserRepositoryActionListener listener) {
        new DeleteTask(listener).execute(user);
    }
    public User getUserByName(String name){
        return appDatabase.userDao().findByName(name);
    }

}
