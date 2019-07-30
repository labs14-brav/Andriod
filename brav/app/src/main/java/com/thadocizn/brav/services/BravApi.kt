package com.thadocizn.brav.services

import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.User
import retrofit2.Call
import retrofit2.http.*

interface BravApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("/cases/{id}")
    fun getSpecificCase(@Path("id")id:String): Call<Case>

    @GET("/cases")
    fun getCases(): Call<List<Case>>

    @POST("/cases")
    fun createCase(@Body body:String): Call<Case>

    @POST("users/auth")
    fun createUser(): Call<User>

}