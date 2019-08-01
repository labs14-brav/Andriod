package com.thadocizn.brav.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var type:String? = null ,
    var email:String? = null,
    var uid:String? = null)
