package com.thadocizn.brav.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.thadocizn.brav.data.UserRoomDBRepo
import com.thadocizn.brav.models.User
import com.thadocizn.brav.services.BravApi
import com.thadocizn.brav.services.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by charles on 24,July,2019
 */
class UserRepository(private val token: String?, private val context: Context) {

    private var users: ArrayList<User> = ArrayList()
    private val mutableLiveData = MutableLiveData<List<User>>()
    private val createUserMutableLiveData = MutableLiveData<User>()

    val userList: MutableLiveData<List<User>>
        get() {
            val service: BravApi = RetroInstance().service(token)
            val call = service.getUsers()

            call.enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    response.body()?.let { users.addAll(it) }
                    println(response.isSuccessful)
                    mutableLiveData.postValue(users)
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    println(t.message)
                }

            })

            return mutableLiveData
        }

    val loginUser: MutableLiveData<User>
        get() {
            val service: BravApi = RetroInstance().service(token)
            val call = service.loginUser()

            call.enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    val userResponse = response.body()
                    val userRoomDBRepo = UserRoomDBRepo(context )
                    userRoomDBRepo.insertUser(userResponse)
                    createUserMutableLiveData.postValue(userResponse)

                }

            })

            return createUserMutableLiveData
        }
}