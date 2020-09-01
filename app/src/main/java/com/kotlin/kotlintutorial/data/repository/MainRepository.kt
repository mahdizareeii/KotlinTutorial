package com.kotlin.kotlintutorial.data.repository

import com.kotlin.kotlintutorial.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

}