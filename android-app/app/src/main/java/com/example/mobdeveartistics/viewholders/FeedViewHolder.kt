package com.example.mobdeveartistics.viewholders

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.squareup.picasso.Picasso

class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mMediaBackground: ImageView =
        itemView.findViewById(R.id.mediaBackground)
    private val mProfileImage: ImageView =
        itemView.findViewById(R.id.profileImage)
    private val mCommentCount: TextView =
        itemView.findViewById(R.id.commentCount)
    private val mLikeCount: TextView =
        itemView.findViewById(R.id.likeCount)
    private val mOriginalPoster: TextView =
        itemView.findViewById(R.id.originalPoster)
    private val mCaption: TextView =
        itemView.findViewById(R.id.caption)
    private val mSong: TextView =
        itemView.findViewById(R.id.songFeedButton)

    fun setmMediaBackground(url: String?) {
        // Log the URL before loading the image
        Log.d("FeedViewHolder", "Loading image from URL: $url")

        // Check if the URL is not null or empty
        if (!url.isNullOrEmpty()) {
            Picasso.get()
                .load(url) // Use the actual URL from the database
//                .placeholder(R.drawable.media_background_1) // Optional: Set a placeholder image
                .error(R.drawable.error_img) // Optional: Set an error image
                .into(mMediaBackground)
        } else {
            // Optionally, set a default image if the URL is null or empty
            mMediaBackground.setImageResource(R.drawable.error_img) // Set an error image or a default image
        }
    }

    fun setmProfileImage(iv: Int) {
        mProfileImage.setImageResource(iv)
    }

    fun setmCommentCount(tv: String?) {
        mCommentCount.text = tv
    }

    fun setmLikeCount(tv: String?) {
        mLikeCount.text = tv
    }

    fun setmOriginalPoster(tv: String?) {
        mOriginalPoster.text = tv
    }

    fun setmCaption(tv: String?) {
        mCaption.text = tv
    }

    private var mediaPlayer: MediaPlayer? = null

    private var isPlaying: Boolean = false // Variable to track if the audio is playing

    fun setmSong(url: String?) {
        mSong.text = "Play Audio" // Set initial text
        var playbackPosition = 0 // Variable to store the playback position

        val handler = Handler(Looper.getMainLooper()) // Handler to update UI
        val updateTimestampRunnable = object : Runnable {
            override fun run() {
                mediaPlayer?.let {
                    if (isPlaying) {
                        val currentTime = it.currentPosition / 1000 // Current time in seconds
                        mSong.text = "Pause Audio - ${formatTime(currentTime)}" // Update button text with timestamp
                        handler.postDelayed(this, 1000) // Repeat every second
                    }
                }
            }
        }

        mSong.setOnClickListener {
            if (isPlaying) {
                // If currently playing, pause the audio
                playbackPosition = mediaPlayer?.currentPosition ?: 0 // Save current position
                mediaPlayer?.pause()
                mSong.text = "Play Audio" // Change button text
                isPlaying = false // Update the playing state
                handler.removeCallbacks(updateTimestampRunnable) // Stop updating timestamp
            } else {
                // If not playing, start or resume the audio
                if (mediaPlayer == null) {
                    // Create a new MediaPlayer instance if it doesn't exist
                    mediaPlayer = MediaPlayer().apply {
                        setDataSource(url) // Set the audio source
                        prepare() // Prepare the MediaPlayer
                        seekTo(playbackPosition) // Seek to the saved position
                        start() // Start playback
                    }
                } else {
                    // Resume playback if MediaPlayer already exists
                    mediaPlayer?.seekTo(playbackPosition) // Seek to the saved position
                    mediaPlayer?.start() // Resume playback
                }
                mSong.text = "Pause Audio - ${formatTime(0)}" // Show "Pause Audio" and initial timestamp
                isPlaying = true // Update the playing state
                handler.post(updateTimestampRunnable) // Start updating timestamp
            }
        }
    }

    // Utility function to format time in mm:ss
    fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val secs = seconds % 60
        return String.format("%02d:%02d", minutes, secs)
    }


    // Don't forget to release the MediaPlayer in the ViewHolder's finalizer
    fun releasePlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

}
