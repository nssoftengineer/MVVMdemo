package com.example.app_kotlin.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.app_kotlin.db.AppDatabase
import com.example.app_kotlin.db.entity.CommentEntity
import com.example.app_kotlin.db.entity.ProductEntity
import com.example.app_kotlin.model.Comment

class Repository() {

    private lateinit var mAppDatabase: AppDatabase
    private lateinit var mObservableProducts: MediatorLiveData<List<ProductEntity>>


    constructor (appDatabase: AppDatabase) : this() {
        this.mAppDatabase = appDatabase

        mObservableProducts = MediatorLiveData<List<ProductEntity>>()
        mObservableProducts.addSource<List<ProductEntity>>(mAppDatabase.productDao().loadAllProducts()) { productEntities ->
            if (mAppDatabase.getDatabaseCreated().getValue() != null) {
                mObservableProducts.postValue(productEntities)
            }
        }

    }

companion object {
    var instance: Repository?=null
    fun getInstance(appDatabase: AppDatabase): Repository {
        if (instance == null) {
            synchronized(Repository::class.java) {
                if (instance == null) {
                    instance = Repository(appDatabase)
                }
            }
        }
        return this!!.instance!!
    }
}


    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    fun getProducts(): LiveData<List<ProductEntity>> {
        return mObservableProducts
    }

    fun loadProduct(productId: Int): LiveData<ProductEntity> {
        return mAppDatabase.productDao().loadProduct(productId)
    }

    fun loadComments(productId: Int): LiveData<List<CommentEntity>> {
        return mAppDatabase.commentDao().loadComments(productId)
    }

    fun searchProducts(query: String): LiveData<List<ProductEntity>> {
        return mAppDatabase.productDao().searchAllProducts(query)
    }
}