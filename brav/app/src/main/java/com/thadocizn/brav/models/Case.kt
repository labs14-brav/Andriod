package com.thadocizn.brav.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Case(
    val userEmail:String,
    @PrimaryKey
    val userUid:String,
    val description:String,
    val dispute:String,
    val parties:String)