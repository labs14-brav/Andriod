package com.thadocizn.brav

interface BravApi {

@Get("users")
Call<List<User>>getUsers
}