package com.example.mobdeveartistics.activities.library

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.adapters.PlaylistRowAdapter
import com.example.mobdeveartistics.models.PlaylistRow
import com.example.mobdeveartistics.models.PlaylistRowGenerator

class PlaylistsActivity : AppCompatActivity() {

    private val playlistRowList: ArrayList<PlaylistRow> = PlaylistRowGenerator.generatePlaylistData()
    private lateinit var rvPlaylists: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.library_playlists)

        // Setting up the main view to handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvPlaylists = findViewById(R.id.rvPlaylists)
        rvPlaylists.adapter = PlaylistRowAdapter(playlistRowList)
        rvPlaylists.layoutManager = LinearLayoutManager(this)
    }

    fun backToLibrary(v: View) {
        finish()
    }
}