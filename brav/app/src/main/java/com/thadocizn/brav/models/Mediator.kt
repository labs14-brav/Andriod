package com.thadocizn.brav.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Mediator(
    @PrimaryKey
    val id:Int,
    val type:String? = null,
    val email:String,
    val uid:String,
    val license:String? = null,
    val experience:String? = null,
    val specialization:String? = null,
    val language:String? = null,
    val professional_bio:String? = null,
    val name:String? = null,
    val mediator_accepted_at:String? = null,
    val mediator_declined_at:String? = null)

/*
"id": 3,
"type": "mediator",
"email": "labs14brav-mediator@gmail.com",
"uid": "3VhJYYOASWPcC499nsrjWM1SCxF3",
"license": null,
"experience": null,
"specialization": null,
"language": null,
"professional_bio": null,
"name": null,
"mediator_accepted_at": null,
"mediator_declined_at": null*/
