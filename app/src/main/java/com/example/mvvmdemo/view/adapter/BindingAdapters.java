package com.example.mvvmdemo.view.adapter;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("visibleGone")
    public static void isShow(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }



}
