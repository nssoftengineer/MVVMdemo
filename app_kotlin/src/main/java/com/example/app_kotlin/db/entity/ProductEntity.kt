package com.example.app_kotlin.db.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.app_kotlin.model.Product

@Entity(tableName = "products")
class ProductEntity : Product {
    @PrimaryKey
    private var id: Int = 0
    private var name: String? = null
    private var description: String? = null
    private var price: Int = 0

    override fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    override fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    override fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    override fun getPrice(): Int {
        return price
    }

    fun setPrice(price: Int) {
        this.price = price
    }

    constructor() {}

    @Ignore
    constructor(id: Int, name: String, description: String, price: Int) {
        this.id = id
        this.name = name
        this.description = description
        this.price = price
    }

    constructor(product: Product) {
        this.id = product.getId()
        this.name = product.getName()
        this.description = product.getDescription()
        this.price = product.getPrice()
    }
}
