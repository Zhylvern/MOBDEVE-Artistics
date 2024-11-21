package com.example.mobdeveartistics.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.models.SongRow
import com.example.mobdeveartistics.viewholders.SongRowViewHolder

class SongRowAdapter(private val dataList: ArrayList<SongRow>) :
    RecyclerView.Adapter<SongRowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongRowViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.library_song_row_item, parent, false)
        return SongRowViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SongRowViewHolder, position: Int) {
        holder.bindSongRowData(dataList.get(position))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}