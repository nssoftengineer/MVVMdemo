

package com.example.mvvmdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.apimodule.api.product.Data;
import com.example.apimodule.api.product.SampleData;
import com.example.mvvmdemo.app.App;
import com.example.mvvmdemo.model.CommentEntity;
import com.example.mvvmdemo.model.ProductEntity;
import com.example.mvvmdemo.model.CommonError;
import com.example.mvvmdemo.utils.Repository;

import java.util.List;

public class ProductViewModel extends BaseViewModel {

    private final LiveData<ProductEntity> mObservableProduct;
    private final MutableLiveData<Data> dataLiveData=new MutableLiveData<>();
    private final MutableLiveData<SampleData> dataLiveDataString=new MutableLiveData<>();
    public ObservableField<ProductEntity> product = new ObservableField<>();
    private Repository repository;
    private final int mProductId;
    private final LiveData<List<CommentEntity>> mObservableComments;

    public ProductViewModel(@NonNull Application application, Repository repository,
                            final int productId) {
        super(application);
        mProductId = productId;
        this.repository=repository;
        mObservableComments = repository.loadComments(mProductId);
        mObservableProduct = repository.loadProduct(mProductId);
    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    public LiveData<List<CommentEntity>> getComments() {
        return mObservableComments;
    }

    public LiveData<ProductEntity> getObservableProduct() {
        return mObservableProduct;
    }

    public void setProduct(ProductEntity product) {
        this.product.set(product);
    }


    /**
     * api call here
     */
    public LiveData<Data> getDataFromApi(LifecycleOwner lifecycleOwner,String action) {
        repository.getDataFromApi(action).observe(lifecycleOwner, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                dataLiveData.setValue(data);
            }
        });
        return dataLiveData;
    }
    public LiveData<SampleData> getDataFromApiSampleData(LifecycleOwner lifecycleOwner,String action) {
        repository.getDataFromApiSampleData(action).observe(lifecycleOwner, new Observer<SampleData>() {
            @Override
            public void onChanged(SampleData data) {
                dataLiveDataString.setValue(data);
            }
        });
        return dataLiveDataString;
    }
    public MutableLiveData<CommonError> getError(LifecycleOwner lifecycleOwner)
    {
         MutableLiveData<CommonError> commonErrorMutableLiveData=new MutableLiveData<>();
         repository.getError().observe(lifecycleOwner, new Observer<CommonError>() {
             @Override
             public void onChanged(CommonError commonError) {
                 commonErrorMutableLiveData.setValue(commonError);
             }
         });

        return commonErrorMutableLiveData;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;
        private final int mProductId;
        private final Repository mRepository;
        public Factory(@NonNull Application application, int productId) {
            mApplication = application;
            mProductId = productId;
            mRepository = ((App) application).getRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ProductViewModel(mApplication, mRepository, mProductId);
        }
    }
}
