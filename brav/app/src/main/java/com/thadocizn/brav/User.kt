package com.thadocizn.brav

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(val userName:String,val type:String, val email:String, val nickName:String)
