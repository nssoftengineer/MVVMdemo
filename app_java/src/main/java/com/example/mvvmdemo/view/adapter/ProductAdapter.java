

package com.example.mvvmdemo.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.databinding.ProductItemBinding;
import com.example.mvvmdemo.model.ProductEntity;
import com.example.mvvmdemo.view.ProductClickCallback;

import java.util.List;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    List<? extends ProductEntity> mProductList;

    @Nullable
    private final ProductClickCallback mProductClickCallback;

    public ProductAdapter(@Nullable ProductClickCallback clickCallback) {
        mProductClickCallback = clickCallback;
        setHasStableIds(true);
    }

    public void setProductList(final List<? extends ProductEntity> productList) {
        if (mProductList == null) {
            mProductList = productList;
            notifyItemRangeInserted(0, productList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mProductList.size();
                }

                @Override
                public int getNewListSize() {
                    return productList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mProductList.get(oldItemPosition).getId() ==
                            productList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    ProductEntity newProduct = productList.get(newItemPosition);
                    ProductEntity oldProduct = mProductList.get(oldItemPosition);
                    return newProduct.getId() == oldProduct.getId()
                            && Objects.equals(newProduct.getDescription(), oldProduct.getDescription())
                            && Objects.equals(newProduct.getName(), oldProduct.getName())
                            && newProduct.getPrice() == oldProduct.getPrice();
                }
            });
            mProductList = productList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.product_item,
                        parent, false);
        binding.setCallback(mProductClickCallback);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.binding.setProduct(mProductList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProductList == null ? 0 : mProductList.size();
    }

    @Override
    public long getItemId(int position) {
        return mProductList.get(position).getId();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        final ProductItemBinding binding;

        public ProductViewHolder(ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
