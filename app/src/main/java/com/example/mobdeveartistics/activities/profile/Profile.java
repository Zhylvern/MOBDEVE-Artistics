package com.example.mobdeveartistics.activities.profile;

import android.net.Uri;
import java.io.Serializable;

public class Profile implements Serializable {
    private String username;
    private String tag;
    private String bio;
    private String profilePictureUriString;

    public Profile(String username, String tag, String bio) {
        this.username = username;
        this.tag = tag;
        this.bio = bio;
        this.profilePictureUriString = null;
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

    public void setProfilePictureUriString(String profilePictureUriString) {
        this.profilePictureUriString = profilePictureUriString;
    }

    public String getProfilePictureUriString() {
        return profilePictureUriString;
    }

    public void setProfilePictureUri(Uri profilePictureUri) {
        this.profilePictureUriString = profilePictureUri != null ? profilePictureUri.toString() : null;
    }

    public Uri getProfilePictureUri() {
        return profilePictureUriString != null ? Uri.parse(profilePictureUriString) : null;
    }
}