package com.example.mobdeveartistics.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.network.feed.Post
import com.example.mobdeveartistics.viewholders.FeedViewHolder

class FeedAdapter(private val dataList: MutableList<Post>) :
    RecyclerView.Adapter<FeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_data_item, parent, false)
        return FeedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val data = dataList[position]

        // Set the media background to imgUrl
        // holder.setmMediaBackground(data.imgUrl)

        // Set the profile image based on the username
        // holder.setmProfileImage(data.username) // Assuming you have a method to handle this

        // Set the comment count
        holder.setmCommentCount(data.commentCount.toString())

        // Set the like count
        holder.setmLikeCount(data.likeCount.toString())

        // Set the original poster (username)
        holder.setmOriginalPoster(data.username)

        // Set the caption
        holder.setmCaption(data.caption)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}