package com.example.mobdeveartistics.activities.library

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.mobdeveartistics.activities.login.LoginActivity
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

    private var accessToken_value: String? = null
    private var userID_value: String? = null

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

        val intent: Intent = intent
        accessToken_value = intent.getStringExtra("accessToken") // Assign to class-level variable
        userID_value = intent.getStringExtra("userID") // Assign to class-level variable

        // Log statements for debugging
        if (accessToken_value != null) {
            Log.d("YourTag", "Access Token: $accessToken_value")
        } else {
            Log.d("YourTag", "Access Token is NULL")
        }

        if (userID_value != null) {
            Log.d("YourTag", "User   ID: $userID_value")
        } else {
            Log.d("YourTag", "User   ID is NULL")
        }

        // Find the nav button layouts
        val homeButton: View = findViewById(R.id.homeButtonLayout)
        val libraryButton: View = findViewById(R.id.libraryButtonLayout)
        val profileButton: View = findViewById(R.id.profileButtonLayout)
        val navLoginButton: View = findViewById(R.id.loginButtonLayout)
        val navLogoutButton: View = findViewById(R.id.logoutButtonLayout)

        // Check if accessToken_value and userID_value are null
        if (accessToken_value == null || userID_value == null) {
            // Hide the buttons if either is null
            homeButton.visibility = View.GONE
            libraryButton.visibility = View.GONE
            profileButton.visibility = View.GONE
            navLoginButton.visibility = View.VISIBLE
            navLogoutButton.visibility = View.GONE
        } else {
            // Show the buttons if both are present
            homeButton.visibility = View.VISIBLE
            libraryButton.visibility = View.VISIBLE
            profileButton.visibility = View.VISIBLE
            navLoginButton.visibility = View.GONE
            navLogoutButton.visibility = View.VISIBLE

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
        val i = Intent(this@LibraryActivity, LikedTracksActivity::class.java)
        startActivity(i)
    }

    fun playlistsClicked(v: View?) {
        val i = Intent(this@LibraryActivity, PlaylistsActivity::class.java
        )
        startActivity(i)
    }

    fun followingClicked(v: View?) {
        val i = Intent(this@LibraryActivity, FollowingActivity::class.java
        )
        startActivity(i)
    }

    fun followersClicked(v: View?) {
        val i = Intent(this@LibraryActivity, FollowersActivity::class.java
        )
        startActivity(i)
    }

    fun yourUploadsClicked(v: View?) {
        val i = Intent(this@LibraryActivity, UploadActivity::class.java
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
    fun nav_home_button(v: View?) { val i = Intent(applicationContext, MainActivity::class.java)
        i.putExtra("accessToken", accessToken_value) // Pass the access token
        i.putExtra("userID", userID_value) // Pass the user ID
        startActivity(i)
    }


    fun nav_library_button(v: View?) {
        val i = Intent(applicationContext, LibraryActivity::class.java)
        i.putExtra("accessToken", accessToken_value) // Pass the access token
        i.putExtra("userID", userID_value) // Pass the user ID
        startActivity(i)
    }

    fun nav_profile_button(v: View?) {
        val i = Intent(applicationContext, ProfileActivity::class.java)
        i.putExtra("accessToken", accessToken_value) // Pass the access token
        i.putExtra("userID", userID_value) // Pass the user ID
        startActivity(i)
    }

    // Temp Login Button
    fun navLoginButton(v: View?) {
        val i = Intent(applicationContext, LoginActivity::class.java)
        startActivity(i)
    }

    fun navLogoutButton(v: View?) {
        // Set accessToken and userID to null
        accessToken_value = null
        userID_value = null

        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)

        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
    }
}

