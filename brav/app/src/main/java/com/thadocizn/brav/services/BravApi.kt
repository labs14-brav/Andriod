package com.thadocizn.brav.services

import androidx.lifecycle.LiveData
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.models.User
import retrofit2.Call
import retrofit2.http.*

interface BravApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("/mediators")
    fun getMediators(): Call<List<Mediator>>

    @GET("/cases/{id}")
    fun getSpecificCase(@Path("id")id:String): Call<Case>

    @GET("/cases")
    fun getCases(): Call<List<Case>>

    @POST("/cases")
    fun createCase(@Body body:String): Call<Case>

    @POST("users/auth")
    fun loginUser(): Call<User>

}