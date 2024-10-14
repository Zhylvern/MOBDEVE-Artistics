package com.example.mobdeveartistics;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    private ImageView mMediaBackground;
    private ImageView mProfileImage;
    private TextView mCommentCount;
    private TextView mLikeCount;
    private TextView mOriginalPoster;
    private TextView mCaption;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mMediaBackground = itemView.findViewById(R.id.mediaBackground);
        this.mProfileImage = itemView.findViewById(R.id.profileImage);
        this.mCommentCount = itemView.findViewById(R.id.commentCount);
        this.mLikeCount = itemView.findViewById(R.id.likeCount);
        this.mOriginalPoster = itemView.findViewById(R.id.originalPoster);
        this.mCaption = itemView.findViewById(R.id.caption);
    }

    public void setmMediaBackground(int iv) {
        this.mMediaBackground.setImageResource(iv);
    }

    public void setmProfileImage(int iv) {
        this.mProfileImage.setImageResource(iv);
    }

    public void setmCommentCount(String tv) {
        this.mCommentCount.setText(tv);
    }

    public void setmLikeCount(String tv) {
        this.mLikeCount.setText(tv);
    }

    public void setmOriginalPoster(String tv) {
        this.mOriginalPoster.setText(tv);
    }

    public void setmCaption(String tv) {
        this.mCaption.setText(tv);
    }
}
