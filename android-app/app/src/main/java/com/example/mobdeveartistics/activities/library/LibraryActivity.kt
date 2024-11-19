package com.example.mobdeveartistics.activities.library

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.activities.feed.MainActivity
import com.example.mobdeveartistics.activities.profile.ProfileActivity
import com.example.mobdeveartistics.adapters.SongRowAdapter
import com.example.mobdeveartistics.models.SongRow
import com.example.mobdeveartistics.models.SongRowGenerator

class LibraryActivity : AppCompatActivity() {
    private var likedTracks: ConstraintLayout? = null
    private var playlists: ConstraintLayout? = null
    private var following: ConstraintLayout? = null
    private var followers: ConstraintLayout? = null
    private var yourUploads: ConstraintLayout? = null
    private var btnSeeAll: TextView? = null

    private val songHistoryList: ArrayList<SongRow> = SongRowGenerator.generateListHistData()
    private lateinit var rvListHist: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.library_activity)

        // Setting up the main view to handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        likedTracks = findViewById(R.id.libCat_likedTracks)
        playlists = findViewById(R.id.libCat_playlists)
        following = findViewById(R.id.libCat_following)
        followers = findViewById(R.id.libCat_followers)
        yourUploads = findViewById(R.id.libCat_yourUploads)
        btnSeeAll = findViewById(R.id.btnSeeAllListHist)

        rvListHist = findViewById(R.id.rvListHist)
        rvListHist.adapter = SongRowAdapter(songHistoryList)
        rvListHist.layoutManager = LinearLayoutManager(this)
    }

    fun likedTracksClicked(v: View?) {
        val i = Intent(
            this@LibraryActivity,
            LikedTracksActivity::class.java
        )
        startActivity(i)
    }

    fun playlistsClicked(v: View?) {
        val i = Intent(
            this@LibraryActivity,
            PlaylistsActivity::class.java
        )
        startActivity(i)
    }

    fun followingClicked(v: View?) {
        val i = Intent(
            this@LibraryActivity,
            FollowingActivity::class.java
        )
        startActivity(i)
    }

    fun followersClicked(v: View?) {
        val i = Intent(
            this@LibraryActivity,
            FollowersActivity::class.java
        )
        startActivity(i)
    }

    fun yourUploadsClicked(v: View?) {
        val i = Intent(
            this@LibraryActivity,
            UploadActivity::class.java
        )
        startActivity(i)
    }

    fun seeAllClicked(v: View?) {
        val toast = Toast.makeText(this@LibraryActivity, "TODO: See all Listening History", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun backToLibrary(v: View) {
        finish()
    }

    //Navbar Buttons
    fun nav_home_button(v: View?) {
        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
    }


    fun nav_library_button(v: View?) {
        val i = Intent(
            applicationContext,
            LibraryActivity::class.java
        )
        startActivity(i)
    }

    fun nav_profile_button(v: View?) {
        val i = Intent(
            applicationContext,
            ProfileActivity::class.java
        )
        startActivity(i)
    }
}

