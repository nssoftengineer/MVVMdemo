package com.example.mvvmdemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.model.Product;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Add product list fragment if this is first creation
        if (savedInstanceState == null) {
            ProductListFragment fragment = new ProductListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ProductListFragment.TAG).commit();
        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }



    /** Shows the product detail fragment */
    public void show(Product product) {

        ProductFragment productFragment = ProductFragment.forProduct(product.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("product")
                .replace(R.id.fragment_container,
                        productFragment, null).commit();
    }



}
