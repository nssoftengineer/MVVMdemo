package com.example.apimodule.api.apiservice;


import com.example.apimodule.api.product.Data;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * createdBy neeraj singh 3/12/2019
 *
 */
public interface LoginService {
    @GET("chou4")
    Single<Data> getLogin();
}