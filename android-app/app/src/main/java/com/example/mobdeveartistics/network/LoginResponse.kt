package com.example.mobdeveartistics.network

data class LoginResponse(
    val message: String,
    val user: User,
    val accessToken: String
)