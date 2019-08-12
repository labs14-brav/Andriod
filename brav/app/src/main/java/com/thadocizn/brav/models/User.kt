package com.thadocizn.brav.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val id:Int,
    val type:String? = null ,
    val email:String,
    val uid:String,
    val license:String,
    val price:Int,
    val experience:String,
    val specialization:String,
    val language:String,
    val professional_bio:String,
    val name:String,
    val deactivated_at:String,
    val mediator_accepted_at:String,
    val mediator_declined_at: String,
    val created_at:String,
    val updated_at:String)
