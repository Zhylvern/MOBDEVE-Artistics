package com.example.mobdeveartistics.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.models.PlaylistRow

class PlaylistRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivPlaylistImage: ImageView = itemView.findViewById(R.id.ivPlaylistImage)
    private val tvPlaylistName: TextView = itemView.findViewById(R.id.tvPlaylistName)
    private val tvPlaylistNumFollowers: TextView = itemView.findViewById(R.id.tvPlaylistNumSongs)

    fun bindPlaylistRowData(playlistRow: PlaylistRow) {
        ivPlaylistImage.setImageResource(playlistRow.playlistImage)
        tvPlaylistName.text = playlistRow.playlistName
        tvPlaylistNumFollowers.text = playlistRow.playlistNumSongs.toString() + " Songs"
    }
}