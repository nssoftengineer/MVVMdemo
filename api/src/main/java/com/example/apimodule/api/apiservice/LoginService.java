package com.example.apimodule.api.apiservice;


import com.example.apimodule.api.product.Data;
import com.example.apimodule.api.product.SampleData;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * createdBy neeraj singh 3/12/2019
 *
 */
public interface LoginService {
    @GET("prod")
    Single<SampleData> getLogin();
}