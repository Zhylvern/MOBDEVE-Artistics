package com.example.mobdeveartistics.activities.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobdeveartistics.R;
import com.example.mobdeveartistics.activities.feed.MainActivity;
import com.example.mobdeveartistics.activities.library.LibraryActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profile_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnClicked(View v){
        Profile profile = new Profile(
                ((TextView) findViewById(R.id.profile_username)).getText().toString(),
                ((TextView) findViewById(R.id.profile_tag)).getText().toString(),
                ((TextView) findViewById(R.id.profile_bio)).getText().toString()
        );

        Intent i = new Intent(getApplicationContext(), EditProfileActivity.class);
        i.putExtra("profile", profile);
        startActivityForResult(i, 1); // Start the activity for result
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Get the updated profile information
            Profile updatedProfile = (Profile) data.getSerializableExtra("updatedProfile");

            // Display the updated profile information
            ((TextView) findViewById(R.id.profile_username)).setText(updatedProfile.getUsername());
            ((TextView) findViewById(R.id.profile_tag)).setText(updatedProfile.getTag());
            ((TextView) findViewById(R.id.profile_bio)).setText(updatedProfile.getBio());

            // Display the profile picture
            String profilePictureUriString = updatedProfile.getProfilePictureUriString();
            if (profilePictureUriString != null && !profilePictureUriString.isEmpty()) {
                ImageView profileImg = findViewById(R.id.profile_img);
                profileImg.setImageURI(Uri.parse(profilePictureUriString));
            }
        }
    }

    public void backToFeed(View v) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    //Navbar Buttons

    public void nav_home_button(View v) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }


    public void nav_library_button (View v) {
        Intent i = new Intent(getApplicationContext(), LibraryActivity.class);
        startActivity(i);
    }

    public void nav_profile_button(View v) {
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(i);
    }
}

