package com.example.mobdeveartistics.network.login

data class LoginResponse(
    val isSuccessful: Boolean,
    val message: String,
    val user: User,
    val accessToken: String
)