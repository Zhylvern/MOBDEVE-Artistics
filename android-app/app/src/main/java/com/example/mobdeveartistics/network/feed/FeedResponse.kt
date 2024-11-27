package com.example.mobdeveartistics.network.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post (
    val id: String,
    val created_at: String,
    val username: String?,
    val caption: String,
    val like_count: Int,
    val comment_count: Int,
    @SerialName(value = "imgUrl")
    val img_url: String,
    val song_url: String
)