package com.example.mobdeveartistics.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.models.PlaylistRow
import com.example.mobdeveartistics.viewholders.PlaylistRowViewHolder

class PlaylistRowAdapter(private val dataList: ArrayList<PlaylistRow>) :
    RecyclerView.Adapter<PlaylistRowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistRowViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.library_playlist_row_item, parent, false)
        return PlaylistRowViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaylistRowViewHolder, position: Int) {
        holder.bindPlaylistRowData(dataList.get(position))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}