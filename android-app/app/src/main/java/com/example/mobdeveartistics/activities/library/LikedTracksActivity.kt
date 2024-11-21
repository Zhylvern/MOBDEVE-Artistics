package com.example.mobdeveartistics.activities.library

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.adapters.SongRowAdapter
import com.example.mobdeveartistics.models.SongRow
import com.example.mobdeveartistics.models.SongRowGenerator

class LikedTracksActivity : AppCompatActivity() {

    private val songList: ArrayList<SongRow> = SongRowGenerator.generateLikedTracksData()
    private lateinit var rvLikedTracks: RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.library_liked_tracks)

        // Setting up the main view to handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvLikedTracks = findViewById(R.id.rvLikedTracks)
        rvLikedTracks.adapter = SongRowAdapter(songList)
        rvLikedTracks.layoutManager = LinearLayoutManager(this)
    }

    fun backToLibrary(v: View) {
        finish()
    }
}