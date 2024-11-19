package com.example.mobdeveartistics.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.models.SongRow

class SongRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivSongImage: ImageView = itemView.findViewById(R.id.ivSongImage)
    private val tvSongTitle: TextView = itemView.findViewById(R.id.tvSongTitle)
    private val tvSongArtist: TextView = itemView.findViewById(R.id.tvSongArtist)
    private val tvSongNumPlays: TextView = itemView.findViewById(R.id.tvSongNumPlays)
    private val tvSongDuration: TextView = itemView.findViewById(R.id.tvSongDuration)

    fun bindSongRowData(songRow: SongRow) {
        ivSongImage.setImageResource(songRow.songImage)
        tvSongTitle.text = songRow.songTitle
        tvSongArtist.text = songRow.songArtist
        tvSongNumPlays.text = songRow.songNumPlays
        tvSongDuration.text = songRow.songDuration
    }
}