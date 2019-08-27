package com.example.app_kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app_kotlin.R
import com.example.app_kotlin.databinding.ProductItemBinding
import com.example.app_kotlin.model.Product
import com.example.app_kotlin.view.ProductClickCallback

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    internal var mProductList: List<Product>? = null

    private var mProductClickCallback: ProductClickCallback?

    constructor(clickCallback: ProductClickCallback?) {
        mProductClickCallback = clickCallback
        setHasStableIds(true)
    }

    fun setProductList(productList: List<Product>) {
        if (mProductList == null) {
            mProductList = productList
            notifyItemRangeInserted(0, productList.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return mProductList!!.size
                }

                override fun getNewListSize(): Int {
                    return productList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return mProductList!![oldItemPosition].getId() == productList[newItemPosition].getId()
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val newProduct = productList[newItemPosition]
                    val oldProduct = mProductList!![oldItemPosition]
                    return (newProduct.getId() == oldProduct.getId()
                            && newProduct.getDescription() == oldProduct.getDescription()
                            && newProduct.getName() == oldProduct.getName()
                            && newProduct.getPrice() == oldProduct.getPrice())
                }
            })
            mProductList = productList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = DataBindingUtil
                .inflate<ProductItemBinding>(LayoutInflater.from(parent.context), R.layout.product_item,
                        parent, false)
        binding.setCallback(mProductClickCallback)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.setProduct(mProductList!![position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (mProductList == null) 0 else mProductList!!.size
    }

    override fun getItemId(position: Int): Long {
        return mProductList!![position].getId().toLong()
    }

    class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.getRoot())
}
