package com.example.mobdeveartistics.viewholders

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
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
        mSong.setOnClickListener {
            if (isPlaying) {
                // If currently playing, pause the audio
                mediaPlayer?.pause()
                mSong.text = "Play Audio" // Change button text
                isPlaying = false // Update the playing state
            } else {
                // If not playing, start the audio
                if (mediaPlayer != null) {
                    mediaPlayer?.release() // Release any existing media player
                }
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(url) // Set the audio source
                    prepare() // Prepare the MediaPlayer
                    start() // Start playback
                }
                mSong.text = "Pause Audio" // Change button text
                isPlaying = true // Update the playing state
            }
        }
    }
    // Don't forget to release the MediaPlayer in the ViewHolder's finalizer
    fun releasePlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

}
