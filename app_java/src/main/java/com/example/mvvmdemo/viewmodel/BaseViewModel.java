

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
import com.example.mvvmdemo.app.App;
import com.example.mvvmdemo.model.CommentEntity;
import com.example.mvvmdemo.model.CommonError;
import com.example.mvvmdemo.model.ProductEntity;
import com.example.mvvmdemo.utils.AppExecutors;
import com.example.mvvmdemo.utils.Repository;

import java.util.List;

public class BaseViewModel extends AndroidViewModel {

    private Repository repository;
    private AppExecutors appExecutors;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.repository = ((App)getApplication()).getRepository();
        this.appExecutors = ((App)getApplication()).getAppExecutors();;
    }

    public Repository getRepository() {
        return repository;
    }

    public AppExecutors getAppExecutors() {
        return appExecutors;
    }
}
