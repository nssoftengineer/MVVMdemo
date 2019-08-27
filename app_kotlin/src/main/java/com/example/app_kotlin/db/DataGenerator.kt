package com.example.app_kotlin.db

import com.example.app_kotlin.db.entity.CommentEntity
import com.example.app_kotlin.db.entity.ProductEntity
import java.util.*
import java.util.concurrent.TimeUnit

class DataGenerator {
    companion object {
    private val FIRST = arrayOf("Special edition", "New", "Cheap", "Quality", "Used")
    private val SECOND = arrayOf("Three-headed Monkey", "Rubber Chicken", "Pint of Grog", "Monocle")
    private val DESCRIPTION = arrayOf("is finally here", "is recommended by Stan S. Stanman", "is the best sold product on Mêlée Island", "is \uD83D\uDCAF", "is ❤️", "is fine")
    private val COMMENTS = arrayOf("Comment 1", "Comment 2", "Comment 3", "Comment 4", "Comment 5", "Comment 6")


        fun generateProducts(): List<ProductEntity> {
            val products = ArrayList<ProductEntity>(FIRST.size * SECOND.size)
            val rnd = Random()
            for (i in FIRST.indices) {
                for (j in SECOND.indices) {
                    val product = ProductEntity()
                    product.setName(FIRST[i] + " " + SECOND[j])
                    product.setDescription(product.getName() + " " + DESCRIPTION[j])
                    product.setPrice(rnd.nextInt(240))
                    product.setId(FIRST.size * i + j + 1)
                    products.add(product)
                }
            }
            return products
        }

        fun generateCommentsForProducts(
                products: List<ProductEntity>): List<CommentEntity> {
            val comments = ArrayList<CommentEntity>()
            val rnd = Random()

            for (product in products) {
                val commentsNumber = rnd.nextInt(5) + 1
                for (i in 0 until commentsNumber) {
                    val comment = CommentEntity()
                    comment.setProductId(product.getId())
                    comment.setText(COMMENTS[i] + " for " + product.getName())
                    comment.setPostedAt(Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis((commentsNumber - i).toLong()) + TimeUnit.HOURS.toMillis(i.toLong())))
                    comments.add(comment)
                }
            }

            return comments
        }
    }

}
