package com.example.mvvmdemo.utils;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.apimodule.api.apiservice.ApiService;
import com.example.apimodule.api.product.Data;
import com.example.mvvmdemo.db.AppDatabase;
import com.example.mvvmdemo.db.entity.CommentEntity;
import com.example.mvvmdemo.db.entity.ProductEntity;
import com.example.mvvmdemo.model.CommonError;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * createdBy neeraj singh 3/12/2019
 *
 */
public class Repository {

    private static final String TAG = "Repository" ;
    private static Repository instance;
    private final AppDatabase mAppDatabase;
    private ApiService apiService;
    private MediatorLiveData<List<ProductEntity>> mObservableProducts;
    private MutableLiveData<Data> mObservableData =new MutableLiveData<>();
    private MutableLiveData<CommonError> error =new MutableLiveData<>();


    public Repository(AppDatabase appDatabase, ApiService apiService) {
        this.mAppDatabase = appDatabase;
        this.apiService = apiService;
        mObservableProducts = new MediatorLiveData<>();
        mObservableProducts.addSource(mAppDatabase.productDao().loadAllProducts(), new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(List<ProductEntity> productEntities) {
                if (mAppDatabase.getDatabaseCreated().getValue() != null) {
                    mObservableProducts.postValue(productEntities);
                }
            }
        });
    }

    public static Repository getInstance(AppDatabase appDatabase, ApiService apiService) {
        if (instance == null) {
            synchronized (Repository.class) {
                if (instance == null) {
                    instance = new Repository(appDatabase, apiService);
                }
            }
        }
        return instance;
    }


    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    public LiveData<List<ProductEntity>> getProducts() {
        return mObservableProducts;
    }

    public LiveData<ProductEntity> loadProduct(final int productId) {
        return mAppDatabase.productDao().loadProduct(productId);
    }

    public LiveData<List<CommentEntity>> loadComments(final int productId) {
        return mAppDatabase.commentDao().loadComments(productId);
    }

    public LiveData<List<ProductEntity>> searchProducts(String query) {
        return mAppDatabase.productDao().searchAllProducts(query);
    }

    public LiveData<Data> getDataFromApi() {
        apiService.getProduct().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Data>() {
                    @Override
                    public void onSuccess(Data data) {
                        mObservableData.setValue(data);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    CommonError commonError=new CommonError();
                    commonError.setThrowable(e);
                    error.setValue(commonError);
                    }
                });
        return mObservableData;
    }

    public MutableLiveData<CommonError> getError() {
        return error;
    }
}
