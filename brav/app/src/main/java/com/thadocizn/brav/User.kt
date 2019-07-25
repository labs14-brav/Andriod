package com.thadocizn.brav

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(val id:Int,val type:String? = null , val email:String, val uid:String)
