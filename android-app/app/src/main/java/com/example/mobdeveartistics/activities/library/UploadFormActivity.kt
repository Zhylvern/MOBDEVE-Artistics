package com.example.mobdeveartistics.activities.library

import Post
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.network.ApiService
import com.example.mobdeveartistics.network.RetrofitApiService
import com.example.mobdeveartistics.network.feed.UserUploadPostRequest
import com.example.mobdeveartistics.network.feed.UserUploadPostResponse
import okhttp3.Call
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import java.io.File
import retrofit2.Callback


class UploadFormActivity : AppCompatActivity() {
    private lateinit var uploadSongButton: Button
    private lateinit var uploadJacketButton: Button
    private lateinit var submitButton: Button
    private lateinit var captionEditText: EditText
    private lateinit var songNameTextView: TextView
    private lateinit var jacketImageView: ImageView

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
        songNameTextView = findViewById(R.id.songNameTextView)
        jacketImageView = findViewById(R.id.jacketImageView)

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
                    songUri?.let {
                        // Display the name of the uploaded song
                        val cursor = contentResolver.query(it, null, null, null, null)
                        cursor?.use {
                            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                            if (it.moveToFirst()) {
                                val songName = it.getString(nameIndex)
                                songNameTextView.text = songName
                            }
                        }
                    }
                }
                REQUEST_CODE_JACKET -> {
                    jacketUri = data.data // Get the selected jacket URI
                    jacketUri?.let {
                        // Display the image preview
                        val inputStream = contentResolver.openInputStream(it)
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        jacketImageView.setImageBitmap(bitmap)
                        jacketImageView.visibility = View.VISIBLE // Make the ImageView visible
                    }
                }
            }
        }
    }

    fun uploadContent() {
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

        val id = ""
        val caption = captionEditText.text.toString()

        val uploadRequest = UserUploadPostRequest(id, caption)

        // Make the API call to upload the content
        RetrofitApiService().getRetrofitInstance().create(ApiService::class.java)
            .uploadPost(uploadRequest).enqueue(object : Callback<UserUploadPostResponse>{
            override fun onResponse(call: retrofit2.Call<UserUploadPostResponse>, response: retrofit2.Response<UserUploadPostResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@UploadFormActivity, "Upload successful!", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity and return to MainActivity
                } else {
                    Toast.makeText(this@UploadFormActivity, "Upload failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<UserUploadPostResponse>, t: Throwable) {
                Toast.makeText(this@UploadFormActivity, "Network error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        private const val REQUEST_CODE_SONG = 1
        private const val REQUEST_CODE_JACKET = 2
    }
}