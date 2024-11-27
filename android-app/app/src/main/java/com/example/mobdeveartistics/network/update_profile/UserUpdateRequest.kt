package com.example.mobdeveartistics.network.update_profile

data class UserUpdateRequest(
    val id: String,
    val name: String,
    val username: String,
    val bio: String
)