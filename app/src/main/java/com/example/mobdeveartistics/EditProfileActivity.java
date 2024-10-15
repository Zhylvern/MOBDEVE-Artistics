package com.example.mobdeveartistics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditProfileActivity extends AppCompatActivity {

    private Profile profile;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();
        profile = (Profile) intent.getSerializableExtra("profile");

        if (profile != null) {
            ((EditText) findViewById(R.id.username)).setText(profile.getUsername());
            ((EditText) findViewById(R.id.tag)).setText(profile.getTag());
            ((EditText) findViewById(R.id.bio)).setText(profile.getBio());
        }
    }

    public void editProfileImage(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            ImageView profileImg = findViewById(R.id.profile_img);
            profileImg.setImageURI(selectedImageUri);
        }
    }

    public void SubmitbtnClicked(View v) {
        // Get the updated profile information
        String username = ((EditText) findViewById(R.id.username)).getText().toString().trim();
        String tag = ((EditText) findViewById(R.id.tag)).getText().toString().trim();
        String bio = ((EditText) findViewById(R.id.bio)).getText().toString().trim();

        // Check if any of the fields are empty
        if (username.isEmpty() || tag.isEmpty() || bio.isEmpty()) {
            // Display an error message to the user
            ((EditText) findViewById(R.id.username)).setError("Please fill in all fields");
            ((EditText) findViewById(R.id.tag)).setError("Please fill in all fields");
            ((EditText) findViewById(R.id.bio)).setError("Please fill in all fields");
        } else {
            // Create a new Profile object with the updated information
            Profile updatedProfile = new Profile(username, tag, bio);

            // If an image has been selected, add it to the profile
            if (selectedImageUri != null) {
                updatedProfile.setProfilePictureUri(selectedImageUri);
            }

            // Pass the updated profile information back to the MainActivity
            Intent intent = new Intent();
            intent.putExtra("updatedProfile", updatedProfile);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}