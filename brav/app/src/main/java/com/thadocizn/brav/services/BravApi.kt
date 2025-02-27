package com.thadocizn.brav.services


import com.thadocizn.brav.models.*
import kotlinx.coroutines.Deferred
import org.json.JSONArray
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
    fun getInvoicesCaseIdAsync(@Path("id") caseId:Int):Deferred<InvoicesResult>

    @POST("/invoices/{id}/charge")
    fun sendTokenAsync(@Path("id")invoiceId: Int, @Body stripeToken:StripeToken):Deferred<JSONArray>

    @POST("/invoices/case/{id}")
    fun createInvoiceAsync(@Path("id") caseId: Int):Deferred<Invoice>

    @POST("/mediators/{id}/cases")
    fun connectAsync(@Path("id") mediatorId: Int, @Body case_id: CaseOut): Deferred<Case>


    @POST("users/auth")
    fun loginUserAsync(): Deferred<User>

    @POST("/cases")
    fun postCaseAsync(@Body case: Case): Deferred<Case>

    @PUT("users/deactivate")
    fun deactivateAsync(): Deferred<User>

    @PUT("/invoices/{id}/")
    fun invoicePaid(@Path("id")invoiceId: Int):Deferred<JSONArray>
}