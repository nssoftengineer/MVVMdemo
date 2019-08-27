package com.example.app_kotlin.model

interface Product {
     fun getId(): Int
     fun getName(): String?
     fun getDescription(): String?
     fun getPrice(): Int
}