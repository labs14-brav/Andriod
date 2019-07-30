package com.thadocizn.brav.models

import com.squareup.moshi.JsonClass
//TODO placeholder
@JsonClass(generateAdapter = true)
data class Mediator(val id:Int, val type:String? = null, val email:String, val uid:String)
