package com.thadocizn.brav.services

import com.thadocizn.brav.models.User
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("users")
    fun getUserList(): Call<List<User>>

}