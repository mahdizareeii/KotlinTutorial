package com.kotlin.kotlintutorial.data.api

import com.kotlin.kotlintutorial.data.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(): Response<List<User>>

}