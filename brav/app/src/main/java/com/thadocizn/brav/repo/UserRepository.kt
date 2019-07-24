package com.thadocizn.brav.repo

import androidx.lifecycle.MutableLiveData
import com.thadocizn.brav.User
import com.thadocizn.brav.services.BravApi
import com.thadocizn.brav.services.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by charles on 24,July,2019
 */
class UserRepository {

    private var users: ArrayList<User>? = null
    private val mutableLiveData = MutableLiveData<List<User>>()

    val userList:MutableLiveData<List<User>>
    get() {
        val service: BravApi = RetroInstance().service
        val call = service.getUsers()

        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                response.body()?.let { users?.addAll(it) }
                println(response.isSuccessful)
                mutableLiveData.postValue(users)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

        return mutableLiveData
    }

}