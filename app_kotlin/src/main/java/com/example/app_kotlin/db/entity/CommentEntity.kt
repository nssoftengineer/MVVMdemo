package com.example.app_kotlin.db.entity

import androidx.room.*
import com.example.app_kotlin.model.Comment

import java.util.*

@Entity(tableName = "comments", foreignKeys = [ForeignKey(entity = ProductEntity::class, parentColumns = ["id"], childColumns = ["productId"], onDelete = ForeignKey.CASCADE)], indices = [Index(value = ["productId"])])
class CommentEntity : Comment {
    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0
    private var productId: Int = 0
    private var text: String? = null
    private var postedAt: Date? = null

    override fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    override fun getProductId(): Int {
        return productId
    }

    fun setProductId(productId: Int) {
        this.productId = productId
    }

    override fun getText(): String? {
        return text
    }

    fun setText(text: String) {
        this.text = text
    }

    override fun getPostedAt(): Date? {
        return postedAt
    }

    fun setPostedAt(postedAt: Date) {
        this.postedAt = postedAt
    }

    constructor() {}

    @Ignore
    constructor(id: Int, productId: Int, text: String, postedAt: Date) {
        this.id = id
        this.productId = productId
        this.text = text
        this.postedAt = postedAt
    }
}
