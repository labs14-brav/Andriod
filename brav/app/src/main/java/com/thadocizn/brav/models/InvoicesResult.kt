package com.thadocizn.brav.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InvoicesResult(
    @Json(name = "invoice")
    val invoice: List<Invoice>,
    @Json(name = "mediator")
    val mediator: Mediator
)