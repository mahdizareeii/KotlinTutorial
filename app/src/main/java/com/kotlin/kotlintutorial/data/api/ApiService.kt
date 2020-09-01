package com.kotlin.kotlintutorial.data.api

import com.kotlin.kotlintutorial.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers():Response<List<User>>

}