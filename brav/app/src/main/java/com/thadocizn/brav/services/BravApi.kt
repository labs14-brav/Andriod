package com.thadocizn.brav.services

import com.thadocizn.brav.models.*
import kotlinx.coroutines.Deferred
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

    @GET("/invoices/case/{id}")
    fun getInvoicesCaseIdAsync(@Path("id") caseId:Int):Deferred<List<Invoices>>

    @GET("/invoices/{id}/session")
    fun getSessionId(@Path("id")invoiceId:Int):Deferred<Invoices>

    @POST("/invoices/case/{id}")
    fun createInvoice(@Path("id") caseId: Int):Deferred<Invoices>

    @POST("/mediators/{id}/cases")
    fun connectAsync(@Path("id") mediatorId: Int, @Body case_id: CaseOut): Deferred<Case>


    @POST("users/auth")
    fun loginUserAsync(): Deferred<User>

    @POST("/cases")
    fun postCaseAsync(@Body case: Case): Deferred<Case>

    @PUT("users/deactivate")
    fun deactivateAsync(): Deferred<User>

    @PUT("/invoices/{id}/")
    fun invoicePaid(@Path("id")invoiceId: Int):Deferred<Invoices>
}