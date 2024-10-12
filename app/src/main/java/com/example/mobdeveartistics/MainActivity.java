package com.example.mobdeveartistics;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView likeButton, commentButton, mediaBackground;
    private TextView likeCount, commentCount, originalPoster, caption;
    private boolean isLiked = false;
    private int likes = 116; // Starting like count

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        likeButton = findViewById(R.id.likeButton);
        commentButton = findViewById(R.id.commentButton);
        mediaBackground = findViewById(R.id.mediaBackground);
        likeCount = findViewById(R.id.likeCount);
        commentCount = findViewById(R.id.commentCount);
        originalPoster = findViewById(R.id.originalPoster);
        caption = findViewById(R.id.caption);

        // Set initial text
        likeCount.setText(String.valueOf(likes));
        commentCount.setText("6");

        // Set up the onClick listeners
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLike();
            }
        });
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle comment button click
                Toast.makeText(MainActivity.this, "Comment button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        mediaBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle background click
                Toast.makeText(MainActivity.this, "Media background clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Toggle like button state and update like count
    private void toggleLike() {
        if (isLiked) {
            likes--;
            likeButton.setImageResource(R.drawable.like); // Change to unliked image
        } else {
            likes++;
            likeButton.setImageResource(R.drawable.liked); // Change to liked image
        }
        isLiked = !isLiked;
        likeCount.setText(String.valueOf(likes));
    }
}