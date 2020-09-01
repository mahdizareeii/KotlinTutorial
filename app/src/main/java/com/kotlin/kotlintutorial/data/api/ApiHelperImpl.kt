package com.kotlin.kotlintutorial.data.api

import com.kotlin.kotlintutorial.data.model.User
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()

}