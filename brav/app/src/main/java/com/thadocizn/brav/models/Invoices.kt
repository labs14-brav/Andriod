package com.thadocizn.brav.models

data class Invoices(
    val mediator_id:Int,
    val case_id:Int,
    val amount:Int,
    val hours:Int,
    val paid_at: String,
    val created_at:String)
