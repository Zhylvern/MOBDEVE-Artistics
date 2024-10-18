package com.example.mobdeveartistics.activities.profile;

import android.net.Uri;

import java.io.Serializable;

public class Profile implements Serializable {
    private String username;
    private String tag;
    private String bio;
    private Uri profilePictureUri;

    public Profile(String username, String tag, String bio) {
        this.username = username;
        this.tag = tag;
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfilePictureUri(Uri profilePictureUri) {
        this.profilePictureUri = profilePictureUri;
    }

    public Uri getProfilePictureUri() {
        return profilePictureUri;
    }
}