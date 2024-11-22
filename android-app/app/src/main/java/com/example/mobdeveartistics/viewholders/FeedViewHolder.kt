package com.example.mobdeveartistics.viewholders

import android.util.Log
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

    fun setmMediaBackground(url: String?) {
        // Log the URL before loading the image
        Log.d("FeedViewHolder", "Loading image from URL: $url")

        // Check if the URL is not null or empty
        if (!url.isNullOrEmpty()) {
            Picasso.get()
                .load(url) // Use the actual URL from the database
                .placeholder(R.drawable.media_background_1) // Optional: Set a placeholder image
                .error(R.drawable.mizuki) // Optional: Set an error image
                .into(mMediaBackground)
        } else {
            // Optionally, set a default image if the URL is null or empty
            mMediaBackground.setImageResource(R.drawable.mizuki) // Set an error image or a default image
        }
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
