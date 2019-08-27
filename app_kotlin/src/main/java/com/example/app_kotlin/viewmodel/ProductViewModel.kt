package com.example.app_kotlin.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app_kotlin.app.App
import com.example.app_kotlin.db.entity.CommentEntity
import com.example.app_kotlin.db.entity.ProductEntity
import com.example.app_kotlin.utils.Repository

class ProductViewModel :AndroidViewModel{
    private var mObservableProduct: LiveData<ProductEntity>

    var product = ObservableField<ProductEntity>()

    private var mProductId: Int

    private var mObservableComments: LiveData<List<CommentEntity>>

    constructor (application: Application, repository: Repository,productId: Int):super(application){
        mProductId = productId

        mObservableComments = repository.loadComments(mProductId)
        mObservableProduct = repository.loadProduct(mProductId)
    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    fun getComments(): LiveData<List<CommentEntity>> {
        return mObservableComments
    }

    fun getObservableProduct(): LiveData<ProductEntity> {
        return mObservableProduct
    }

    fun setProduct(product: ProductEntity) {
        this.product.set(product)
    }


    class Factory(private val mApplication: Application, private val mProductId: Int) : ViewModelProvider.NewInstanceFactory() {

        private val mRepository: Repository

        init {
            mRepository = (mApplication as App).getRepository()
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return ProductViewModel(mApplication, mRepository, mProductId) as T
        }
    }
}