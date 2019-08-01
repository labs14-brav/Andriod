package com.thadocizn.brav.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.thadocizn.brav.data.UserRoomDBRepo
import com.thadocizn.brav.models.User
import com.thadocizn.brav.repo.UserRepository

/**
 * Created by charles on 24,July,2019
 */
class UserViewModel(val token: String?, val context: Context) : ViewModel() {

    private val repo: UserRepository
    private val userRoomDBRepo:UserRoomDBRepo
    private var user: LiveData<User>? = null

    init {
        repo = UserRepository(token, context)
        userRoomDBRepo = UserRoomDBRepo(context)
    }

    val userList: LiveData<List<User>>
        get() = repo.userList

    val createUser: Unit
        get() {
            user = repo.loginUser

        }

    val getUser: LiveData<User>?
    get() {
        user = userRoomDBRepo.getUser()
        return user
    }

}