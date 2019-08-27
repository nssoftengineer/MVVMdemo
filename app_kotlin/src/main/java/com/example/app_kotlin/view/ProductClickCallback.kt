package com.example.app_kotlin.view

import com.example.app_kotlin.model.Product

interface ProductClickCallback {
    fun onClick(product: Product)
}