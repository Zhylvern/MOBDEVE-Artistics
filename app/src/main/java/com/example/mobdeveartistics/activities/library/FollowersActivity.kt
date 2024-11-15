package com.example.mobdeveartistics.activities.library

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobdeveartistics.R

class FollowersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_followers) // Ensure this layout exists

        // Setting up the main view to handle window insets
        val mainView = findViewById<View>(R.id.main) // Ensure this ID matches your layout

        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Adjust the padding of the view based on the system bars
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets // Return the insets
        }
    }

    fun backToLibrary(v: View?) {
        finish() // Close the activity
    }
}