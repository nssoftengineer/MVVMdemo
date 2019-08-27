package com.example.app_kotlin.view.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app_kotlin.R
import com.example.app_kotlin.databinding.CommentItemBinding
import com.example.app_kotlin.model.Comment
import com.example.app_kotlin.view.CommentClickCallback

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private var mCommentList: List<Comment>? = null

    private var mCommentClickCallback: CommentClickCallback?

    constructor(commentClickCallback: CommentClickCallback?) {
        mCommentClickCallback = commentClickCallback
    }

    fun setCommentList(comments: List<Comment>) {
        if (mCommentList == null) {
            mCommentList = comments
            notifyItemRangeInserted(0, comments.size)
        } else {
            val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return mCommentList!!.size
                }

                override fun getNewListSize(): Int {
                    return comments.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val old = mCommentList!![oldItemPosition]
                    val comment = comments[newItemPosition]
                    return old.getId() == comment.getId()
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val old = mCommentList!![oldItemPosition]
                    val comment = comments[newItemPosition]
                    return (old.getId() == comment.getId()
                            && old.getPostedAt() === comment.getPostedAt()
                            && old.getProductId() == comment.getProductId()
                            && old.getText() == comment.getText())
                }
            })
            mCommentList = comments
            diffResult.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = DataBindingUtil
                .inflate<CommentItemBinding>(LayoutInflater.from(parent.context), R.layout.comment_item,
                        parent, false)
        binding.setCallback(mCommentClickCallback)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.binding.setComment(mCommentList!![position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (mCommentList == null) 0 else mCommentList!!.size
    }

    class CommentViewHolder(val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.getRoot())
}
