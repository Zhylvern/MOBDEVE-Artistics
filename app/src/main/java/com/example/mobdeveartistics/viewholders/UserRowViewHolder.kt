package com.example.mobdeveartistics.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.models.UserRow

class UserRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivUserImage: ImageView = itemView.findViewById(R.id.ivUserImage)
    private val tvUsername: TextView = itemView.findViewById(R.id.tvUsername)
    private val tvUserNumFollowers: TextView = itemView.findViewById(R.id.tvUserNumFollowers)

    fun bindUserRowData(userRow: UserRow) {
        ivUserImage.setImageResource(userRow.userImage)
        tvUsername.text = userRow.username
        tvUserNumFollowers.text = userRow.userNumFollowers.toString() + " Followers"
    }
}