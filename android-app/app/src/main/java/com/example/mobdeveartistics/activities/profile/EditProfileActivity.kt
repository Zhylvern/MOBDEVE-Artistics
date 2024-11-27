package com.example.mobdeveartistics.activities.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.activities.feed.MainActivity
import com.example.mobdeveartistics.activities.login.LoginActivity
import com.example.mobdeveartistics.network.ApiService
import com.example.mobdeveartistics.network.RetrofitApiService
import com.example.mobdeveartistics.network.login.LoginRequest
import com.example.mobdeveartistics.network.login.LoginResponse
import com.example.mobdeveartistics.network.register.RegisterRequest
import com.example.mobdeveartistics.network.update_profile.UserUpdateRequest
import com.example.mobdeveartistics.network.update_profile.UserUpdateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : AppCompatActivity() {
    private var profile: Profile? = null
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity_edit)

        val intent = intent
        profile = intent.getSerializableExtra("profile") as Profile?

        if (profile != null) {
            (findViewById<View>(R.id.username) as EditText).setText(profile!!.username)
            (findViewById<View>(R.id.tag) as EditText).setText(profile!!.tag)
            (findViewById<View>(R.id.bio) as EditText).setText(profile!!.bio)
        }
    }

    fun editProfileImage(v: View?) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.data
            val profileImg = findViewById<ImageView>(R.id.profile_img)
            profileImg.setImageURI(selectedImageUri)
        }
    }

    fun SubmitbtnClicked(v: View?) {
        try {
            // Get the updated profile information
            val name = (findViewById<View>(R.id.username) as EditText).text.toString().trim()
            val username = (findViewById<View>(R.id.tag) as EditText).text.toString().trim()
            val bio = (findViewById<View>(R.id.bio) as EditText).text.toString().trim()

            val intent: Intent = intent
            val id = intent.getStringExtra("userID")

            // Check if any of the fields are empty
            if (name.isEmpty() || username.isEmpty() || bio.isEmpty()) {
                // Display an error message to the user
                if (name.isEmpty()) {
                    (findViewById<View>(R.id.username) as EditText).error = "Please fill in this field"
                }
                if (username.isEmpty()) {
                    (findViewById<View>(R.id.tag) as EditText).error = "Please fill in this field"
                }
                if (bio.isEmpty()) {
                    (findViewById<View>(R.id.bio) as EditText).error = "Please fill in this field"
                }
                return // Early return to prevent the request from being sent
            }

            val userUpdateRequest = UserUpdateRequest(id.toString(), name, username, bio)

            RetrofitApiService().getRetrofitInstance().create(ApiService::class.java)
                .postUserProfile(userUpdateRequest).enqueue(object : Callback<UserUpdateResponse> {
                override fun onResponse(call: Call<UserUpdateResponse>, response: Response<UserUpdateResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@EditProfileActivity, "Updated user information.", Toast.LENGTH_SHORT).show()
                        // Create a new Profile object with the updated information
                        val updatedProfile = Profile(name, username, bio)

                        // If an image has been selected, add it to the profile
                        if (selectedImageUri != null) {
                            // Convert Uri to String for serialization
                            updatedProfile.profilePictureUriString = selectedImageUri.toString()
                        }

                        // Pass the updated profile information back to the MainActivity
                        val intent = Intent()
                        intent.putExtra("updatedProfile", updatedProfile)
                        setResult(RESULT_OK, intent)
                        finish()
                    } else {
                        Toast.makeText(this@EditProfileActivity, "There was an issue with updating user information", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserUpdateResponse>, t: Throwable) {
                    Toast.makeText(this@EditProfileActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
            Log.e("EditProfileActivity", "Exception occurred: ${e.message}")
            Toast.makeText(this, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}