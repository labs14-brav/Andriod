package com.thadocizn.brav.services

import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.models.User
import retrofit2.Call
import retrofit2.http.*

interface BravApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("/cases")
    fun getCases(): Call<List<Case>>

    @GET("/mediators")
    fun getMediators(@Query("price") price:String,
                     @Query("experience")language:String,
                     @Query("specialization")experience:String,
                     @Query("language")specialty:String): Call<List<Mediator>>

    @POST("users/auth")
    fun loginUser(): Call<User>

    @POST("/cases")
    fun postCase(@Body case: Case): Call<Case>


}