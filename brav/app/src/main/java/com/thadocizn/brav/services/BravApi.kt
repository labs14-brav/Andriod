package com.thadocizn.brav.services

import com.thadocizn.brav.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface BravApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    //Todo change value to match BE
    @POST("users/auth")
    fun createUser(@Header("authorization") token: String?): Call<User>

}