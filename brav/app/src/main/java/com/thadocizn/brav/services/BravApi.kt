package com.thadocizn.brav.services

import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.CaseOut
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.models.User
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

interface BravApi {

    @GET("/cases")
    fun getCasesAsync(): Deferred<List<Case>>

    @GET("/mediators")
    fun getMediatorsAsync(
        @Query("price") price: String,
        @Query("experience") experience: String,
        @Query("specialization") specialization: String,
        @Query("language") language: String
    ): Deferred<List<Mediator>>

    @POST("/mediators/{id}/cases")
    fun connectAsync(@Path("id") mediatorId: Int, @Body case_id: CaseOut): Deferred<Case>


    @POST("users/auth")
    fun loginUserAsync(): Deferred<User>

    @POST("/cases")
    fun postCaseAsync(@Body case: Case): Deferred<Case>

    @PUT("users/deactivate")
    fun deactivateAsync(): Deferred<User>
}