package com.example.randomuser.data

import UserData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

private val BASE_URL = "https://randomuser.me/"
private const val ALL_USER = "api/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ApiService {
    @GET(ALL_USER)
    suspend fun getAllUsers(): UserData
}

object UserApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}