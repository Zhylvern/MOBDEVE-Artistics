package com.example.mobdeveartistics.activities.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobdeveartistics.R;

public class LibraryActivity extends AppCompatActivity {

    private ConstraintLayout likedTracks, playlists, following, followers, yourUploads;
    private ConstraintLayout song1, song2, song3;
    private TextView btnSeeAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_library);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        likedTracks = findViewById(R.id.libCat_likedTracks);
        playlists = findViewById(R.id.libCat_playlists);
        following = findViewById(R.id.libCat_following);
        followers = findViewById(R.id.libCat_followers);
        yourUploads = findViewById(R.id.libCat_yourUploads);
        song1 = findViewById(R.id.hist_song1);
        song2 = findViewById(R.id.hist_song2);
        song3 = findViewById(R.id.hist_song3);
        btnSeeAll = findViewById(R.id.lib_btnSeeAll);
    }

    public void likedTracksClicked(View v) {

        Intent i = new Intent(LibraryActivity.this, LikedTracksActivity.class);
        startActivity(i);

        Toast toast = Toast.makeText(LibraryActivity.this, "Liked tracks clicked!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void playlistsClicked(View v) {

        Intent i = new Intent(LibraryActivity.this, PlaylistsActivity.class);
        startActivity(i);

        Toast toast = Toast.makeText(LibraryActivity.this, "Playlists clicked!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void followingClicked(View v) {

        Intent i = new Intent(LibraryActivity.this, FollowingActivity.class);
        startActivity(i);

        Toast toast = Toast.makeText(LibraryActivity.this, "Following clicked!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void followersClicked(View v) {

        Intent i = new Intent(LibraryActivity.this, FollowersActivity.class);
        startActivity(i);

        Toast toast = Toast.makeText(LibraryActivity.this, "Followers clicked!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void yourUploadsClicked(View v) {

        Toast toast = Toast.makeText(LibraryActivity.this, "Your uploads clicked!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void songClicked(View v) {

        Toast toast = Toast.makeText(LibraryActivity.this, "Song X clicked!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void seeAllClicked(View v) {

        Toast toast = Toast.makeText(LibraryActivity.this, "See all clicked!", Toast.LENGTH_SHORT);
        toast.show();

    }

    public void backToLibrary(View v) {
        finish();
    }
}