package com.example.app_kotlin.db.entity

import androidx.room.Entity
import androidx.room.Fts4

@Entity(tableName = "productsFts")
@Fts4(contentEntity = ProductEntity::class)
class ProductFtsEntity(val name: String, val description: String)
