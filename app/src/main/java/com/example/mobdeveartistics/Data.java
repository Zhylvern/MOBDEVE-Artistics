package com.example.mobdeveartistics;

public class Data {
    private int mediaBackground;
    private int profileImage;
    private int likes;
    private int comments;
    private String name;
    private String caption;

    public Data(int mediaBackground, int profileImage, int likes, int comments, String name, String caption) {
        this.mediaBackground = mediaBackground;
        this.profileImage = profileImage;
        this.likes = likes;
        this.comments = comments;
        this.name = name;
        this.caption = caption;
    }

    public int getMediaBackgroundResource() {
        return mediaBackground;
    }

    public int getProfileImageResource() {
        return profileImage;
    }

    public int getLikeCount() {
        return likes;
    }

    public int getCommentCount() {
        return comments;
    }

    public String getOriginalPoster() {
        return name;
    }

    public String getCaption() {
        return caption;
    }
}