package com.example.mvvmdemo.app;

import android.app.Application;

import com.example.mvvmdemo.db.AppDatabase;
import com.example.mvvmdemo.utils.AppExecutors;
import com.example.mvvmdemo.utils.Repository;

public class App extends Application {

    AppExecutors appExecutors;
    @Override
    public void onCreate() {
        super.onCreate();
        appExecutors=new AppExecutors();

    }

    public AppDatabase getDatabase(){
        return AppDatabase.getInstance(this,appExecutors);
    }

    public Repository getRepository()
    {
        return Repository.getInstance(getDatabase());
    }

}
