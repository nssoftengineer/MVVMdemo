package com.example.mvvmdemo.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.mvvmdemo.api.apiservice.ApiService;
import com.example.mvvmdemo.db.AppDatabase;
import com.example.mvvmdemo.db.entity.CommentEntity;
import com.example.mvvmdemo.db.entity.ProductEntity;

import java.util.List;

public class Repository {

    private static Repository instance;
    private final AppDatabase mAppDatabase;
    private ApiService apiService;
    private MediatorLiveData<List<ProductEntity>> mObservableProducts;


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
}
