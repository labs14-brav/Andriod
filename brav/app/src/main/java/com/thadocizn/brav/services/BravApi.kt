package com.thadocizn.brav.services

import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface BravApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("/cases/{id}")
    fun getCases(@Path("id")id:String): Call<List<Case>>

    @GET("/mediators")
    fun getMediators(): Call<List<Mediator>>

    @POST("users/auth")
    fun loginUser(): Call<User>

    @POST("/cases/{id}")
    fun postCase(@Path("id")id:String): Call<Case>


}