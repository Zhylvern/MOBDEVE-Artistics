package com.example.mobdeveartistics.network

import com.example.mobdeveartistics.network.feed.Post
import com.example.mobdeveartistics.network.login.LoginRequest
import com.example.mobdeveartistics.network.login.LoginResponse
import com.example.mobdeveartistics.network.register.RegisterRequest
import com.example.mobdeveartistics.network.register.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun register(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @GET("posts")
    fun getPosts(): Call<List<Post>>
}