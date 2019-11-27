package com.example.mvvmdemo.app;

import android.app.Application;

import com.example.mvvmdemo.api.ApiClient;
import com.example.mvvmdemo.api.apiservice.ApiService;
import com.example.mvvmdemo.db.AppDatabase;
import com.example.mvvmdemo.utils.AppExecutors;
import com.example.mvvmdemo.utils.Repository;

public class App extends Application {

    private final String BASE_URL ="https://api.myjson.com/bins/" ;
    private AppExecutors appExecutors;
    private ApiService apiService;
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


    public ApiService getApiService() {
        if (apiService == null) {
            synchronized (ApiService.class) {
                if (apiService == null) {
                    apiService = ApiClient.getClient(getApplicationContext(), BASE_URL).create(ApiService.class);
                }
            }
        }
        return apiService;
    }

}
