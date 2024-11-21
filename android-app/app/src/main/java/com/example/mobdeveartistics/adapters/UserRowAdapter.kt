package com.example.mobdeveartistics.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.models.UserRow
import com.example.mobdeveartistics.viewholders.UserRowViewHolder

class UserRowAdapter(private val dataList: ArrayList<UserRow>) :
    RecyclerView.Adapter<UserRowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRowViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.library_user_row_item, parent, false)
        return UserRowViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserRowViewHolder, position: Int) {
        holder.bindUserRowData(dataList.get(position))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}