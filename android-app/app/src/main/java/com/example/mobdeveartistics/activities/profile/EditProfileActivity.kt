package com.example.mobdeveartistics.activities.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeveartistics.R

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
        // Get the updated profile information
        val username =
            (findViewById<View>(R.id.username) as EditText).text.toString().trim { it <= ' ' }
        val tag = (findViewById<View>(R.id.tag) as EditText).text.toString().trim { it <= ' ' }
        val bio = (findViewById<View>(R.id.bio) as EditText).text.toString().trim { it <= ' ' }

        // Check if any of the fields are empty
        if (username.isEmpty() || tag.isEmpty() || bio.isEmpty()) {
            // Display an error message to the user
            (findViewById<View>(R.id.username) as EditText).error =
                "Please fill in all fields"
            (findViewById<View>(R.id.tag) as EditText).error =
                "Please fill in all fields"
            (findViewById<View>(R.id.bio) as EditText).error =
                "Please fill in all fields"
        } else {
            // Create a new Profile object with the updated information
            val updatedProfile = Profile(username, tag, bio)

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
        }
    }
}