package com.example.mobdeveartistics.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R

class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mMediaBackground: ImageView =
        itemView.findViewById(R.id.mediaBackground)
    private val mProfileImage: ImageView =
        itemView.findViewById(R.id.profileImage)
    private val mCommentCount: TextView =
        itemView.findViewById(R.id.commentCount)
    private val mLikeCount: TextView =
        itemView.findViewById(R.id.likeCount)
    private val mOriginalPoster: TextView =
        itemView.findViewById(R.id.originalPoster)
    private val mCaption: TextView =
        itemView.findViewById(R.id.caption)

    fun setmMediaBackground(iv: Int) {
        mMediaBackground.setImageResource(iv)
    }

    fun setmProfileImage(iv: Int) {
        mProfileImage.setImageResource(iv)
    }

    fun setmCommentCount(tv: String?) {
        mCommentCount.text = tv
    }

    fun setmLikeCount(tv: String?) {
        mLikeCount.text = tv
    }

    fun setmOriginalPoster(tv: String?) {
        mOriginalPoster.text = tv
    }

    fun setmCaption(tv: String?) {
        mCaption.text = tv
    }
}
