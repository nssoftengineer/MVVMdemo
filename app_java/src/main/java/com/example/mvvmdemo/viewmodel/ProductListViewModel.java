

package com.example.mvvmdemo.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.mvvmdemo.app.App;
import com.example.mvvmdemo.db.entity.ProductEntity;
import com.example.mvvmdemo.utils.Repository;

import java.util.List;


/**
 * createdBy neeraj singh 2/12/2019
 *
 */
public class ProductListViewModel extends AndroidViewModel {

    private final Repository mRepository;

    private final MediatorLiveData<List<ProductEntity>> mObservableProducts;

    public ProductListViewModel(Application application) {
        super(application);

        mObservableProducts = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableProducts.setValue(null);

        mRepository = ((App) application).getRepository();
        LiveData<List<ProductEntity>> products = mRepository.getProducts();

        // observe the changes of the products from the database and forward them
        mObservableProducts.addSource(products, (List<ProductEntity> value) -> {
            mObservableProducts.setValue(value);
        });
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<ProductEntity>> getProducts() {
        return mObservableProducts;
    }

    public LiveData<List<ProductEntity>> searchProducts(String query) {
        return mRepository.searchProducts(query);
    }
}
