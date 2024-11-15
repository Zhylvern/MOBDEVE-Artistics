package com.example.mobdeveartistics.models

import com.example.mobdeveartistics.R

class SongRowGenerator {
    companion object{
        fun generateLikedTracksData(): ArrayList<SongRow> {
            var tempList = ArrayList<SongRow>()
            tempList.add(SongRow(R.drawable.track_default, "Liked Song Title 1", "Song Artist 1", "21.2K", "3:19"))
            tempList.add(SongRow(R.drawable.track_default, "Liked Song Title 2", "Song Artist 2", "21.2K", "3:19"))
            tempList.add(SongRow(R.drawable.track_default, "Liked Song Title 3", "Song Artist 3", "21.2K", "3:19"))
            tempList.add(SongRow(R.drawable.track_default, "Liked Song Title 4", "Song Artist 3", "21.2K", "3:19"))
            tempList.add(SongRow(R.drawable.track_default, "Liked Song Title 5", "Song Artist 3", "21.2K", "3:19"))

            return tempList
        }

        fun generateUploadData(): ArrayList<SongRow> {
            var tempList = ArrayList<SongRow>()
            tempList.add(SongRow(R.drawable.track_default, "My Song Title 1", "Current User", "21.2K", "3:19"))
            tempList.add(SongRow(R.drawable.track_default, "My Song Title 2", "Current User", "21.2K", "3:19"))
            tempList.add(SongRow(R.drawable.track_default, "My Song Title 3", "Current User", "21.2K", "3:19"))
            tempList.add(SongRow(R.drawable.track_default, "My Song Title 4", "Current User", "21.2K", "3:19"))
            tempList.add(SongRow(R.drawable.track_default, "My Song Title 5", "Current User", "21.2K", "3:19"))

            return tempList
        }

        fun generateListHistData(): ArrayList<SongRow> {
            var tempList = ArrayList<SongRow>()
            tempList.add(SongRow(R.drawable.track_default, "Song Title 1", "Song Artist 1", "21.2K", "3:19"))
            tempList.add(SongRow(R.drawable.track_default, "Song Title 2", "Song Artist 2", "21.2K", "3:19"))
            tempList.add(SongRow(R.drawable.track_default, "Song Title 3", "Song Artist 3", "21.2K", "3:19"))

            return tempList
        }
    }
}