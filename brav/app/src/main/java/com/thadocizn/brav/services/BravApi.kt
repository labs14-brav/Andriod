package com.thadocizn.brav.services

import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.CaseOut
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.models.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface BravApi {

    @GET("users/auth")
    fun getUser(): Call<User>

    @GET("/cases")
    fun getCases(): Call<List<Case>>

    @GET("/cases/{id}/pending-cases")
    fun getPendingCases(@Path("id") userId:Int):Call<List<Case>>

    @GET("/mediators")
    fun getMediators(
        @Query("price") price: String,
        @Query("experience") experience: String,
        @Query("specialization") specialization: String,
        @Query("language") language: String
    ): Call<List<Mediator>>

    @POST("/mediators/{id}/cases")
    fun connect(@Path("id") mediatorId: Int, @Body case_id: CaseOut): Call<Case>

    @POST("users/auth")
    fun loginUser(): Call<User>

    @POST("/cases")
    fun postCase(@Body case: Case): Call<Case>

    @PUT("users/deactivate")
    fun deactivate(): Call<User>
}