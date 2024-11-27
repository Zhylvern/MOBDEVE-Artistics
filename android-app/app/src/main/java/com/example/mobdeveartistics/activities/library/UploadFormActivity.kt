package com.example.mobdeveartistics.activities.library

import Post
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.network.ApiService
import com.example.mobdeveartistics.network.RetrofitApiService
import okhttp3.Call
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import java.io.File

class UploadFormActivity : AppCompatActivity() {
    private lateinit var uploadSongButton: Button
    private lateinit var uploadJacketButton: Button
    private lateinit var submitButton: Button
    private lateinit var captionEditText: EditText

    private var songUri: Uri? = null
    private var jacketUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.library_upload_form)

        // Initialize views
        uploadSongButton = findViewById(R.id.uploadSongButton)
        uploadJacketButton = findViewById(R.id.uploadJacketButton)
        submitButton = findViewById(R.id.submitButton)
        captionEditText = findViewById(R.id.captionEditText)

        // Set onClickListeners for upload buttons
        uploadSongButton.setOnClickListener {
            // Launch intent to select audio file
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "audio/mpeg" // For MP3 files
            startActivityForResult(intent, REQUEST_CODE_SONG)
        }

        uploadJacketButton.setOnClickListener {
            // Launch intent to select image file
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*" // For images
            startActivityForResult(intent, REQUEST_CODE_JACKET)
        }

        // Set up the submit button
        submitButton.setOnClickListener {
            uploadContent()
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_CODE_SONG -> {
                    songUri = data.data // Get the selected song URI
                }
                REQUEST_CODE_JACKET -> {
                    jacketUri = data.data // Get the selected jacket URI
                }
            }
        }
    }

    private fun uploadContent() {
        val caption = captionEditText.text.toString()

        // Create a Retrofit instance
        val retrofit = RetrofitApiService().getRetrofitInstance()
        val apiService = retrofit.create(ApiService::class.java)

        // Create a request body for the files and caption
        val requestFileSong = songUri?.let {
            RequestBody.create(MediaType.parse("audio/mpeg"), File(it.path))
        }
        val requestFileImage = jacketUri?.let {
            RequestBody.create(MediaType.parse("image/jpeg"), File(it.path))
        }

        // Create MultipartBody.Part for the files
        val multipartSong = requestFileSong?.let {
            MultipartBody.Part.createFormData("song", "song.mp3", it)
        }
        val multipartImage = requestFileImage?.let {
            MultipartBody.Part.createFormData("image", "jacket.jpg", it)
        }
//
//        // Make the API call to upload the content
//        apiService.uploadPost(multipartImage, multipartSong, RequestBody.create(MediaType.parse("text/plain"), caption)).enqueue(object : Callback<Post> {
//            override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                if (response.isSuccessful) {
//                    Toast.makeText(this@UploadFormActivity, "Upload successful!", Toast.LENGTH_SHORT).show()
//                    finish() // Close the activity and return to MainActivity
//                } else {
//                    Toast.makeText(this@UploadFormActivity, "Upload failed: ${response.message()}", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Post>, t: Throwable) {
//                Toast.makeText(this@UploadFormActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    companion object {
        private const val REQUEST_CODE_SONG = 1
        private const val REQUEST_CODE_JACKET = 2
    }
}