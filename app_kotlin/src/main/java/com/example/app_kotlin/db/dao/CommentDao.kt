package com.example.app_kotlin.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.app_kotlin.db.entity.CommentEntity
import com.example.app_kotlin.model.Comment

@Dao
interface CommentDao {

    @Query("select * from comments where productId = :productId")
    fun loadComments(productId: Int): LiveData<List<CommentEntity>>

    @Query("select * from comments where productId = :productId")
    fun loadCommentSync(productId:Int):List<CommentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(comments: List<CommentEntity>)
}