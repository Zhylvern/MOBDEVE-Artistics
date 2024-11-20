package com.example.mobdeveartistics.activities.feed

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.activities.library.LibraryActivity
import com.example.mobdeveartistics.activities.login.LoginActivity
import com.example.mobdeveartistics.activities.profile.ProfileActivity
import com.example.mobdeveartistics.adapters.FeedAdapter
import com.example.mobdeveartistics.models.DataGenerator

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapter: FeedAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feed_activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val dataList = DataGenerator.generateData()
        adapter = FeedAdapter(dataList)
        recyclerView?.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

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