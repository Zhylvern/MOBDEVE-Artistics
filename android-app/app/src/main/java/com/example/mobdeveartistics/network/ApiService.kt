package com.example.mobdeveartistics.network

import com.example.mobdeveartistics.network.login.LoginRequest
import com.example.mobdeveartistics.network.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}