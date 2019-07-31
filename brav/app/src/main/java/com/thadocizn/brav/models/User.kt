package com.thadocizn.brav.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class User(

    val id:Int,
    val type:String? = null ,
    val email:String,
    @PrimaryKey
    val uid:String)
