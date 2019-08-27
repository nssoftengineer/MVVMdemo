package com.example.app_kotlin.model

import java.util.*

interface Comment {
     fun getId(): Int
     fun getProductId(): Int
     fun getText(): String?
     fun getPostedAt(): Date?
}