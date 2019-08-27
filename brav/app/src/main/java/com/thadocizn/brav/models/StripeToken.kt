package com.thadocizn.brav.models

import com.squareup.moshi.JsonClass

/**
 * Created by charles on 27,August,2019
 */
@JsonClass(generateAdapter = true)
data class StripeToken(
    var stripeToken: String)