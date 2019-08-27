package com.example.app_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.example.app_kotlin.app.App
import com.example.app_kotlin.db.entity.ProductEntity
import com.example.app_kotlin.utils.Repository

class ProductListViewModel:AndroidViewModel {
    private var mRepository: Repository

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private var mObservableProducts: MediatorLiveData<List<ProductEntity>>

    constructor (application: Application) : super(application) {
        mObservableProducts = MediatorLiveData<List<ProductEntity>>()
        // set by default null, until we get data from the database.
        mObservableProducts.setValue(null)

        mRepository = (application as App).getRepository()
        val products = mRepository.getProducts()

        // observe the changes of the products from the database and forward them
        mObservableProducts.addSource<List<ProductEntity>>(products, Observer<List<ProductEntity>> { mObservableProducts.setValue(it) })
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    fun getProducts(): LiveData<List<ProductEntity>> {
        return mObservableProducts
    }

    fun searchProducts(query: String): LiveData<List<ProductEntity>> {
        return mRepository.searchProducts(query)
    }

}