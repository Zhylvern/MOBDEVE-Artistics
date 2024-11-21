package com.example.mobdeveartistics.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.models.Data
import com.example.mobdeveartistics.viewholders.FeedViewHolder

class FeedAdapter(private val dataList: ArrayList<Data>) :
    RecyclerView.Adapter<FeedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.feed_data_item, parent, false)
        return FeedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val data = dataList[position]

        holder.setmMediaBackground(data.mediaBackgroundResource)
        holder.setmProfileImage(data.profileImageResource)
        holder.setmCommentCount(data.commentCount.toString())
        holder.setmLikeCount(data.likeCount.toString())
        holder.setmOriginalPoster(data.originalPoster)
        holder.setmCaption(data.caption)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}