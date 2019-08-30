package com.example.app_kotlin.view.adapter

import android.view.View
import androidx.databinding.BindingAdapter

class BindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("visibleGone")
        fun isShow(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}