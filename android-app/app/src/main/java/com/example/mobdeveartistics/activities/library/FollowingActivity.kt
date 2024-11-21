package com.example.mobdeveartistics.activities.library

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.adapters.UserRowAdapter
import com.example.mobdeveartistics.models.UserRow
import com.example.mobdeveartistics.models.UserRowGenerator

class FollowingActivity : AppCompatActivity() {

    private val userRowList: ArrayList<UserRow> = UserRowGenerator.generateFollowingData()
    private lateinit var rvFollowing: RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.library_following)

        // Setting up the main view to handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvFollowing = findViewById(R.id.rvFollowing)
        rvFollowing.adapter = UserRowAdapter(userRowList)
        rvFollowing.layoutManager = LinearLayoutManager(this)
    }

    fun backToLibrary(v: View) {
        finish()
    }
}