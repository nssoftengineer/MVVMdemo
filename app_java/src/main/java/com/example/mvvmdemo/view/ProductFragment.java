

package com.example.mvvmdemo.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.apimodule.api.product.Content;
import com.example.apimodule.api.product.Data;
import com.example.apimodule.api.product.Product;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.databinding.ProductFragmentBinding;
import com.example.mvvmdemo.db.entity.CommentEntity;
import com.example.mvvmdemo.db.entity.ProductEntity;
import com.example.mvvmdemo.model.Comment;
import com.example.mvvmdemo.model.CommonError;
import com.example.mvvmdemo.view.adapter.CommentAdapter;
import com.example.mvvmdemo.viewmodel.ProductViewModel;
import com.example.utils.Helper;

import java.util.List;

public class ProductFragment extends Fragment {

    private static final String KEY_PRODUCT_ID = "product_id";

    private ProductFragmentBinding mBinding;

    private CommentAdapter mCommentAdapter;
    private ProductViewModel model;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.product_fragment, container, false);

        // Create and set the adapter for the RecyclerView.
        mCommentAdapter = new CommentAdapter(mCommentClickCallback);
        mBinding.commentList.setAdapter(mCommentAdapter);
        progressBar = ((MainActivity)getActivity()).progressBar;
        return mBinding.getRoot();
    }

    private final CommentClickCallback mCommentClickCallback = new CommentClickCallback() {
        @Override
        public void onClick(Comment comment) {
            if(Helper.isOnline(getActivity())) {
                showProgress();
                //api call
                model.getDataFromApi(getViewLifecycleOwner()).observe(getViewLifecycleOwner(), new Observer<Data>() {
                    @Override
                    public void onChanged(Data data) {
                        String productName = "";
                        for (Content content : data.getContent()) {
                            if (content.getProducts() != null)
                                for (Product product : content.getProducts()) {
                                    if (product != null)
                                        productName += product.getName() + "\n";
                                }
                        }
                        Toast.makeText(getActivity(), productName, Toast.LENGTH_LONG).show();
                        hideProgress();
                    }
                });
            }else{
                Toast.makeText(getActivity(),"No internet.",Toast.LENGTH_LONG).show();
            }
        }
    };

    public void onError() {
        model.getError(getViewLifecycleOwner()).observe(getViewLifecycleOwner(), new Observer<CommonError>() {
            @Override
            public void onChanged(CommonError commonError) {
                Toast.makeText(getActivity(), commonError.getThrowable().getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProductViewModel.Factory factory = new ProductViewModel.Factory(
                getActivity().getApplication(), getArguments().getInt(KEY_PRODUCT_ID));

        model = ViewModelProviders.of(this, factory)
                .get(ProductViewModel.class);


        mBinding.setProductViewModel(model);
        onError();
        subscribeToModel();
    }

    private void subscribeToModel() {

        // Observe product data
        model.getObservableProduct().observe(this, new Observer<ProductEntity>() {
            @Override
            public void onChanged(@Nullable ProductEntity productEntity) {
                model.setProduct(productEntity);
            }
        });

        // Observe comments
        model.getComments().observe(this, new Observer<List<CommentEntity>>() {
            @Override
            public void onChanged(@Nullable List<CommentEntity> commentEntities) {
                if (commentEntities != null) {
                    mBinding.setIsLoading(false);
                    mCommentAdapter.setCommentList(commentEntities);
                } else {
                    mBinding.setIsLoading(true);
                }
            }
        });
    }

    /**
     * Creates product fragment for specific product ID
     */
    public static ProductFragment forProduct(int productId) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
