package com.example.mobdeveartistics.activities.feed

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.activities.library.LibraryActivity
import com.example.mobdeveartistics.activities.login.LoginActivity
import com.example.mobdeveartistics.activities.profile.ProfileActivity
import com.example.mobdeveartistics.adapters.FeedAdapter
import com.example.mobdeveartistics.network.ApiService
import com.example.mobdeveartistics.network.RetrofitApiService
import com.example.mobdeveartistics.network.feed.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private var recyclerView: RecyclerView? = null
    private var adapter: FeedAdapter? = null
    private var postList: MutableList<Post> = mutableListOf() // Mutable list to hold posts
    private var accessToken_value: String? = null
    private var userID_value: String? = null

    override fun onResume() {
        super.onResume()
        fetchPosts() // Refresh the posts when returning to MainActivity
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.feed_activity_main)

        // Retrieve the intent and its extras
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

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        // Fetch posts from the API
        fetchPosts()
    }

    private fun fetchPosts() {
        RetrofitApiService().getRetrofitInstance().create(ApiService::class.java)
            .getPosts().enqueue(object : Callback<List<Post>> { // Change FeedResponse to List<Post>
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if (response.isSuccessful) {
                        val posts = response.body() // Get the list of posts directly
                        if (posts != null) {
                            postList.clear() // Clear existing data
                            postList.addAll(posts) // Add all posts to the list
                            adapter = FeedAdapter(postList) // Create a new adapter with the posts
                            recyclerView?.adapter = adapter // Set the adapter to the RecyclerView
                            adapter?.notifyDataSetChanged() // Notify the adapter of data changes
                        } else {
                            Toast.makeText(this@MainActivity, "No posts available.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Failed to fetch posts. Please try again.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
   // Navbar
    fun onClickProfile(v: View?) {
        val i = Intent(applicationContext, ProfileActivity::class.java)
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