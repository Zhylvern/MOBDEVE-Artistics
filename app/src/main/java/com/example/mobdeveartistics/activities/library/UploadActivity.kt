package com.example.mobdeveartistics.activities.library

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.adapters.SongRowAdapter
import com.example.mobdeveartistics.models.SongRow
import com.example.mobdeveartistics.models.SongRowGenerator

class UploadActivity : AppCompatActivity() {

    private val uploadsList: ArrayList<SongRow> = SongRowGenerator.generateUploadData()
    private lateinit var rvUploads: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.library_upload)

        // Setting up the main view to handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvUploads = findViewById(R.id.rvUploads)
        rvUploads.adapter = SongRowAdapter(uploadsList)
        rvUploads.layoutManager = LinearLayoutManager(this)
    }

    fun uploadSong(v: View) {
        Toast.makeText(this, "TODO: Upload a Song", Toast.LENGTH_SHORT).show();
    }

    fun backToLibrary(v: View) {
        finish()
    }
}