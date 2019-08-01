package com.thadocizn.brav.models

data class Mediator(val id:Int,
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