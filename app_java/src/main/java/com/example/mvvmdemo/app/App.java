package com.example.mvvmdemo.app;

import android.app.Application;

import com.example.apimodule.api.ApiClient;
import com.example.apimodule.api.apiservice.ApiService;
import com.example.mvvmdemo.db.AppDatabase;
import com.example.mvvmdemo.utils.AppExecutors;
import com.example.mvvmdemo.utils.Repository;

import retrofit2.Retrofit;


/**
 * createdBy neeraj singh 2/12/2019
 *
 */
public class App extends Application {

    private final String BASE_URL ="https://api.myjson.com/bins/" ; // TODO: 12/5/2019 dummy api data

    private AppExecutors appExecutors;
    private ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        appExecutors=new AppExecutors();
        ApiClient.retrofitInit(getApplicationContext(), BASE_URL,true);
    }

    public AppDatabase getDatabase(){
        return AppDatabase.getInstance(this,appExecutors);
    }

    public Repository getRepository()
    {
        return Repository.getInstance(getDatabase());
    }


    public AppExecutors getAppExecutors() {
        return appExecutors;
    }
}
