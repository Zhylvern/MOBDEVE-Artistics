package com.example.mobdeveartistics.network.feed

data class Post (
    val id: String,
    val createdAt: String,
    val username: String?,
    val caption: String,
    val likeCount: Int,
    val commentCount: Int,
    val imgUrl: String,
    val songUrl: String
)