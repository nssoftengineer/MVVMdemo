package com.example.mvvmdemo.api.apiservice;


import com.example.mvvmdemo.model.Data;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {
    @GET("chou4")
    Single<Data> getProduct();
}