package com.thadocizn.brav.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.thadocizn.brav.models.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by charles on 31,July,2019
 */
class UserRoomDBRepo(context: Context) {

    private var user:LiveData<User>? = null

    var userDAO: UserDAO

    init {
        val db = AppDatabase.getAppDataBase(context)
        userDAO = db!!.userDAO()

    }

    fun insertUser(user: User?){
//        println(user)
        GlobalScope.launch {
            userDAO.insertUser(user)
        }

    }
//todo fix this
    fun getUser(): LiveData<User>? {
        GlobalScope.launch {
          user = userDAO.getUser()
            println(user.toString())

        }
return user
    }
}