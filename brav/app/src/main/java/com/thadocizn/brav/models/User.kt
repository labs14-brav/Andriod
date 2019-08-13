package com.thadocizn.brav.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val id:Int,
    val type:String? = null ,
    val email:String,
    val uid:String,
    val license:String?=null,
    val price:Int=0,
    val experience:String?=null,
    val specialization:String?=null,
    val language:String?=null,
    val professional_bio:String?=null,
    val name:String?=null,
    val deactivated_at:String?=null,
    val mediator_accepted_at:String?=null,
    val mediator_declined_at: String?=null,
    val created_at:String,
    val updated_at:String)
