package com.example.app_kotlin.view

import com.example.app_kotlin.model.Comment

interface CommentClickCallback {
    fun onClick(comment: Comment)
}