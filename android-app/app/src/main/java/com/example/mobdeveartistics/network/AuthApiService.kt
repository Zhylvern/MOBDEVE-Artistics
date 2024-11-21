package com.example.mobdeveartistics.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


private const val BASE_URL = "http://10.0.2.2:3000"

class AuthApiService {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    public fun getRetrofitInstance(): Retrofit {
        return retrofit
    }
}