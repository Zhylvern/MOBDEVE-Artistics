package com.example.mobdeveartistics.activities.library

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.activities.feed.MainActivity
import com.example.mobdeveartistics.activities.profile.ProfileActivity

class LibraryActivity : AppCompatActivity() {
    private var likedTracks: ConstraintLayout? = null
    private var playlists: ConstraintLayout? = null
    private var following: ConstraintLayout? = null
    private var followers: ConstraintLayout? = null
    private var yourUploads: ConstraintLayout? = null
    private var song1: ConstraintLayout? = null
    private var song2: ConstraintLayout? = null
    private var song3: ConstraintLayout? = null
    private var btnSeeAll: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_library)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        likedTracks = findViewById(R.id.libCat_likedTracks)
        playlists = findViewById(R.id.libCat_playlists)
        following = findViewById(R.id.libCat_following)
        followers = findViewById(R.id.libCat_followers)
        yourUploads = findViewById(R.id.libCat_yourUploads)
        song1 = findViewById(R.id.hist_song1)
        song2 = findViewById(R.id.hist_song2)
        song3 = findViewById(R.id.hist_song3)
        btnSeeAll = findViewById(R.id.lib_btnSeeAll)
    }

    fun likedTracksClicked(v: View?) {
        val i = Intent(
            this@LibraryActivity,
            LikedTracksActivity::class.java
        )
        startActivity(i)

        val toast =
            Toast.makeText(this@LibraryActivity, "Liked tracks clicked!", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun playlistsClicked(v: View?) {
        val i = Intent(
            this@LibraryActivity,
            PlaylistsActivity::class.java
        )
        startActivity(i)

        val toast = Toast.makeText(this@LibraryActivity, "Playlists clicked!", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun followingClicked(v: View?) {
        val i = Intent(
            this@LibraryActivity,
            FollowingActivity::class.java
        )
        startActivity(i)

        val toast = Toast.makeText(this@LibraryActivity, "Following clicked!", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun followersClicked(v: View?) {
        val i = Intent(
            this@LibraryActivity,
            FollowersActivity::class.java
        )
        startActivity(i)

        val toast = Toast.makeText(this@LibraryActivity, "Followers clicked!", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun yourUploadsClicked(v: View?) {
        val i = Intent(
            this@LibraryActivity,
            UploadActivity::class.java
        )
        startActivity(i)

        val toast =
            Toast.makeText(this@LibraryActivity, "Your uploads clicked!", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun songClicked(v: View?) {
        val toast = Toast.makeText(this@LibraryActivity, "Song X clicked!", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun seeAllClicked(v: View?) {
        val toast = Toast.makeText(this@LibraryActivity, "See all clicked!", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun backToLibrary(v: View?) {
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

