package com.thadocizn.brav.services

import com.thadocizn.brav.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface BravApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    //Todo do more research
    @POST("register")
    fun createUser(): Call<Unit>

}