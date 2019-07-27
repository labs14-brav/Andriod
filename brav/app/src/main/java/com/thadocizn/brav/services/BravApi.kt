package com.thadocizn.brav.services

import com.thadocizn.brav.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface BravApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @POST("users/auth")
    fun createUser(@Header("authorization") token: String?): Call<User>

}