package com.example.app_kotlin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_kotlin.R
import com.example.app_kotlin.model.Product

class MainActivity:AppCompatActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Add product list fragment if this is first creation
        if (savedInstanceState == null) {
            val fragment = ProductListFragment()

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ProductListFragment.TAG).commit()
        }
    }

    /** Shows the product detail fragment  */
    fun show(product: Product) {
        val productFragment = ProductFragment.forProduct(product.getId())

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("product")
                .replace(R.id.fragment_container,
                        productFragment, null).commit()
    }
}
