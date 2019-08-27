package com.example.app_kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.app_kotlin.R
import com.example.app_kotlin.databinding.ProductFragmentBinding
import com.example.app_kotlin.db.entity.CommentEntity
import com.example.app_kotlin.db.entity.ProductEntity
import com.example.app_kotlin.view.adapter.CommentAdapter
import com.example.app_kotlin.viewmodel.ProductViewModel

class ProductFragment : Fragment() {

    companion object {
        /** Creates product fragment for specific product ID  */
        private val KEY_PRODUCT_ID = "product_id"
        fun forProduct(productId: Int): ProductFragment {
            val fragment = ProductFragment()
            val args = Bundle()
            args.putInt(KEY_PRODUCT_ID, productId)
            fragment.setArguments(args)
            return fragment
        }
    }


    private var mBinding: ProductFragmentBinding? = null

    private var mCommentAdapter: CommentAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate<ProductFragmentBinding>(inflater, R.layout.product_fragment, container, false)

        // Create and set the adapter for the RecyclerView.
        mCommentAdapter = CommentAdapter(mCommentClickCallback)
        mBinding!!.commentList.setAdapter(mCommentAdapter)
        return mBinding!!.getRoot()
    }

    private val mCommentClickCallback = CommentClickCallback {
        // no-op
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ProductViewModel.Factory(
                getActivity()!!.getApplication(), getArguments()!!.getInt(KEY_PRODUCT_ID))

        val model = ViewModelProviders.of(this, factory)
                .get(ProductViewModel::class.java!!)



        mBinding!!.setProductViewModel(model)

        subscribeToModel(model)
    }

    private fun subscribeToModel(model: ProductViewModel) {

        // Observe product data
        model.getObservableProduct().observe(this, Observer<ProductEntity> { productEntity -> model.setProduct(productEntity) })

        // Observe comments
        model.getComments().observe(this, Observer<List<CommentEntity>> { commentEntities ->
            if (commentEntities != null) {
                mBinding!!.setIsLoading(false)
                mCommentAdapter!!.setCommentList(commentEntities)
            } else {
                mBinding!!.setIsLoading(true)
            }
        })
    }


}
