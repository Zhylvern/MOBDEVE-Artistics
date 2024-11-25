package com.example.mobdeveartistics.activities.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.activities.feed.MainActivity
import com.example.mobdeveartistics.activities.library.LibraryActivity



class ProfileActivity : AppCompatActivity() {
    private var accessToken_value: String? = null
    private var userID_value: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.profile_activity)
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
    }

    fun btnClicked(v: View?) {
        val profile = Profile(
            (findViewById<View>(R.id.profile_username) as TextView).text.toString(),
            (findViewById<View>(R.id.profile_tag) as TextView).text.toString(),
            (findViewById<View>(R.id.profile_bio) as TextView).text.toString()
        )

        val i = Intent(
            applicationContext,
            EditProfileActivity::class.java
        )
        i.putExtra("profile", profile)
        startActivityForResult(i, 1) // Start the activity for result
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Get the updated profile information
            val updatedProfile = data.getSerializableExtra("updatedProfile") as Profile?

            // Display the updated profile information
            (findViewById<View>(R.id.profile_username) as TextView).text =
                updatedProfile!!.username
            (findViewById<View>(R.id.profile_tag) as TextView).text =
                updatedProfile.tag
            (findViewById<View>(R.id.profile_bio) as TextView).text =
                updatedProfile.bio

            // Display the profile picture
            val profilePictureUriString = updatedProfile.profilePictureUriString
            if (profilePictureUriString != null && !profilePictureUriString.isEmpty()) {
                val profileImg = findViewById<ImageView>(R.id.profile_img)
                profileImg.setImageURI(Uri.parse(profilePictureUriString))
            }
        }
    }

    fun backToFeed(v: View?) {
        val i = Intent(applicationContext, MainActivity::class.java)
        i.putExtra("accessToken", accessToken_value) // Pass the access token
        i.putExtra("userID", userID_value) // Pass the user ID
        startActivity(i)
    }

    //Navbar Buttons
    fun nav_home_button(v: View?) {
        val i = Intent(applicationContext, MainActivity::class.java)
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
}

