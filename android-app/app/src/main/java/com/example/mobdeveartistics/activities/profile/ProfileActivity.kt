package com.example.mobdeveartistics.activities.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.activities.feed.MainActivity
import com.example.mobdeveartistics.activities.library.LibraryActivity
import com.example.mobdeveartistics.activities.login.LoginActivity
import com.example.mobdeveartistics.adapters.FeedAdapter
import com.example.mobdeveartistics.network.ApiService
import com.example.mobdeveartistics.network.RetrofitApiService
import com.example.mobdeveartistics.network.feed.Post
import com.example.mobdeveartistics.network.user_profile.UserProfileRequest
import com.example.mobdeveartistics.network.user_profile.UserProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileActivity : AppCompatActivity() {
    private var accessToken_value: String? = null
    private var userID_value: String? = null

    // Variables to hold user profile data
    private var username: String? = null
    private var tag: String? = null
    private var bio: String? = null
    private var profilePictureUriString: String? = null

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
        Log.d("YourTag", "Access Token: $accessToken_value")
        Log.d("YourTag", "User   ID: $userID_value")

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

            // Fetch user data
            fetchUserData()
        }
    }

    private fun fetchUserData() {
        RetrofitApiService().getRetrofitInstance().create(ApiService::class.java)
            .getUserProfile(userID_value.toString()).enqueue(object : Callback<List<UserProfileResponse>> {
            override fun onResponse(call: Call<List<UserProfileResponse>>, response: Response<List<UserProfileResponse>>) {
                if (response.isSuccessful) {
                    val userProfiles = response.body() // Get the list of user profiles directly
                    if (userProfiles != null && userProfiles.isNotEmpty()) {
                        val userProfile = userProfiles[0]
                        username = userProfile.name // Assuming 'name' is the correct field
                        tag = userProfile.username // Assuming 'username' is the correct field
                        bio = userProfile.bio // Assuming 'bio' is the correct field

                        // Update the UI with the fetched data
                        updateUI()
                    } else {
                        Toast.makeText(this@ProfileActivity, "Unable to fetch user data", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@ProfileActivity, "Failed to fetch user data.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<UserProfileResponse>>, t: Throwable) {
                Toast.makeText(this@ProfileActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateUI() {
        // Update the UI elements with the fetched user data
        findViewById<TextView>(R.id .profile_username).text = username
        findViewById<TextView>(R.id.profile_tag).text = tag
        findViewById<TextView>(R.id.profile_bio).text = bio

        // Display the profile picture if available
        if (!profilePictureUriString.isNullOrEmpty()) {
            val profileImg = findViewById<ImageView>(R.id.profile_img)
            profileImg.setImageURI(Uri.parse(profilePictureUriString))
        }
    }

    fun btnClicked(v: View?) {
        val profile = Profile(
            (findViewById<View>(R.id.profile_username) as TextView).text.toString(),
            (findViewById<View>(R.id.profile_tag) as TextView).text.toString(),
            (findViewById<View>(R.id.profile_bio) as TextView).text.toString()
        )

        val i = Intent(applicationContext, EditProfileActivity::class.java)
        i.putExtra("profile", profile)
        startActivityForResult(i, 1) // Start the activity for result
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Get the updated profile information
            val updatedProfile = data.getSerializableExtra("updatedProfile") as Profile?

            // Display the updated profile information
            (findViewById<View>(R.id.profile_username) as TextView).text = updatedProfile!!.username
            (findViewById<View>(R.id.profile_tag) as TextView).text = updatedProfile.tag
            (findViewById<View>(R.id.profile_bio) as TextView).text = updatedProfile.bio

            // Display the profile picture
            val profilePictureUriString = updatedProfile.profilePictureUriString
            if (profilePictureUriString != null && profilePictureUriString.isNotEmpty()) {
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

    // Navbar Buttons
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