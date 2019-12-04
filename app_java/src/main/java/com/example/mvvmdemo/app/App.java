package com.example.mvvmdemo.app;

import android.app.Application;

import com.example.apimodule.api.apiservice.ApiService;
import com.example.mvvmdemo.db.AppDatabase;
import com.example.mvvmdemo.utils.AppExecutors;
import com.example.mvvmdemo.utils.Repository;

import static com.example.apimodule.api.ApiClient.getClient;


/**
 * createdBy neeraj singh 2/12/2019
 *
 */
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
        return Repository.getInstance(getDatabase(),getApiService());
    }

    public ApiService getApiService() {
        if (apiService == null) {
            synchronized (ApiService.class) {
                if (apiService == null) {
                    apiService = getClient(getApplicationContext(), BASE_URL).create(ApiService.class);
                }
            }
        }
        return apiService;
    }

}
