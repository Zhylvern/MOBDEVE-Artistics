package com.example.mobdeveartistics.network

import com.example.mobdeveartistics.network.feed.Post
import com.example.mobdeveartistics.network.login.LoginRequest
import com.example.mobdeveartistics.network.login.LoginResponse
import com.example.mobdeveartistics.network.register.RegisterRequest
import com.example.mobdeveartistics.network.register.RegisterResponse
import com.example.mobdeveartistics.network.update_profile.UserUpdateRequest
import com.example.mobdeveartistics.network.update_profile.UserUpdateResponse
import com.example.mobdeveartistics.network.user_profile.UserProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun register(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("profiles/user")
    fun getUserProfile(@Query("userId") userId: String): Call<List<UserProfileResponse>>

    @PUT("profiles/user/update")
    fun postUserProfile(@Body userUpdateRequest: UserUpdateRequest): Call<UserUpdateResponse>

    @Multipart
    @POST("posts")
    fun uploadPost(
        @Part image: MultipartBody.Part?,
        @Part song: MultipartBody.Part?,
        @Part("caption") caption: RequestBody
    ): Call<Post>
}