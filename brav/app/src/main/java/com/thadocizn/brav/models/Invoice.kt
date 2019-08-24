package com.thadocizn.brav.models


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    var paidAt: String?,
    @Json(name = "updated_at")
    val updatedAt: String
):Parcelable