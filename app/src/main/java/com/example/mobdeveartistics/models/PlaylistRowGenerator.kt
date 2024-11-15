package com.example.mobdeveartistics.models

import com.example.mobdeveartistics.R

class PlaylistRowGenerator {
    companion object{
        fun generatePlaylistData(): ArrayList<PlaylistRow> {
            var tempList = ArrayList<PlaylistRow>()
            tempList.add(PlaylistRow(R.drawable.track_default, "Playlist Name 1", 10))
            tempList.add(PlaylistRow(R.drawable.track_default, "Playlist Name 2", 20))
            tempList.add(PlaylistRow(R.drawable.track_default, "Playlist Name 3", 30))

            return tempList
        }
    }
}