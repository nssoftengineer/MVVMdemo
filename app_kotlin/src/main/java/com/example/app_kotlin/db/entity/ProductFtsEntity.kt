package com.example.app_kotlin.db.entity

import androidx.room.Entity
import androidx.room.Fts4

class ProductFtsEntity {
    private lateinit var name: String
    private lateinit var description: String

    fun ProductFtsEntity(name: String, description: String) {
        this.name = name
        this.description = description
    }

    fun getName(): String? {
        return name
    }

    fun getDescription(): String? {
        return description
    }
}