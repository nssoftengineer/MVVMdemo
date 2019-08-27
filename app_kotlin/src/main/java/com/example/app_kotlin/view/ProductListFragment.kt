package com.example.app_kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.app_kotlin.R
import com.example.app_kotlin.databinding.ListFragmentBinding

import com.example.app_kotlin.db.entity.ProductEntity
import com.example.app_kotlin.model.Product
import com.example.app_kotlin.view.adapter.ProductAdapter
import com.example.app_kotlin.viewmodel.ProductListViewModel

class ProductListFragment : Fragment() {

    companion object {
        val TAG = "ProductListFragmentKot"
    }


    private var mProductAdapter: ProductAdapter? = null

    private var mBinding: ListFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate<ListFragmentBinding>(inflater, R.layout.list_fragment, container, false)

        mProductAdapter = ProductAdapter(mProductClickCallback)
        mBinding!!.productsList.setAdapter(mProductAdapter)

        return mBinding!!.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java!!)

        mBinding!!.productsSearchBtn.setOnClickListener(View.OnClickListener {
            val query = mBinding!!.productsSearchBox.getText()
            if (query == null || query!!.toString().isEmpty()) {
                subscribeUi(viewModel.getProducts())
            } else {
                subscribeUi(viewModel.searchProducts("*$query*"))
            }
        })

        subscribeUi(viewModel.getProducts())
    }

    private fun subscribeUi(liveData: LiveData<List<ProductEntity>>) {
        // Update the list when the data changes
        liveData.observe(this, Observer<List<ProductEntity>> { myProducts ->
            if (myProducts != null) {
                mBinding!!.setIsLoading(false)
                mProductAdapter!!.setProductList(myProducts)
            } else {
                mBinding!!.setIsLoading(true)
            }
            // espresso does not know how to wait for data binding's loop so we execute changes
            // sync.
            mBinding!!.executePendingBindings()
        })
    }

    private val mProductClickCallback = object : ProductClickCallback {
        override fun onClick(product: Product) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                (getActivity() as MainActivity).show(product)
            }
        }
    }
}
