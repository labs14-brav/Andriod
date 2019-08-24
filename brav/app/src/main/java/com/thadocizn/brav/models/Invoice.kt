package com.thadocizn.brav.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Invoice(
    @Json(name = "amount")
    val amount: Int,
    @Json(name = "case_id")
    val caseId: Int,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "hours")
    val hours: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "mediator_id")
    val mediatorId: Int,
    @Json(name = "paid_at")
    var paidAt: Any?,
    @Json(name = "updated_at")
    val updatedAt: String
)