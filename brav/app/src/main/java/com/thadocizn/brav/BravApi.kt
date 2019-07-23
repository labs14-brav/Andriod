package com.thadocizn.brav

import retrofit2.Call
import retrofit2.http.GET

interface BravApi {

@GET("User")
fun getUsers():Call<User>
}