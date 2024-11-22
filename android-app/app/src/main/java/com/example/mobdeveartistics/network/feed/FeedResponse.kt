package com.example.mobdeveartistics.network.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post (
    val id: String,
    val createdAt: String,
    val username: String?,
    val caption: String,
    val likeCount: Int,
    val commentCount: Int,
    @SerialName(value = "imgUrl")
    val imgUrl: String,
    val songUrl: String

)