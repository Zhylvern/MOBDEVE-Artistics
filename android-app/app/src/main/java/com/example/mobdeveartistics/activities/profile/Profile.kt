package com.example.mobdeveartistics.activities.profile

import android.net.Uri
import java.io.Serializable

class Profile(@JvmField var username: String, @JvmField var tag: String, @JvmField var bio: String) : Serializable {
    @JvmField
    var profilePictureUriString: String? = null

    var profilePictureUri: Uri?
        get() = if (profilePictureUriString != null) Uri.parse(
            profilePictureUriString
        ) else null
        set(profilePictureUri) {
            this.profilePictureUriString =
                profilePictureUri?.toString()
        }
}