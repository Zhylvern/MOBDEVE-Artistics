package com.example.mobdeveartistics.activities.feed

import android.content.Intent
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feed_activity_main)

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
        startActivity(i)
    }

    // Navbar Buttons
    fun nav_home_button(v: View?) {
        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
    }

    fun nav_library_button(v: View?) {
        val i = Intent(applicationContext, LibraryActivity::class.java)
        startActivity(i)
    }

    fun nav_profile_button(v: View?) {
        val i = Intent(applicationContext, ProfileActivity::class.java)
        startActivity(i)
    }

    // Temp Login Button
    fun navLoginButton(v: View?) {
        val i = Intent(applicationContext, LoginActivity::class.java)
        startActivity(i)
    }
}