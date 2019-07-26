package com.thadocizn.brav.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.thadocizn.brav.User
import com.thadocizn.brav.repo.UserRepository

/**
 * Created by charles on 24,July,2019
 */
class UserViewModel(val token: String?) : ViewModel() {

    private val repo: UserRepository

    init {
        repo = UserRepository(token)
    }

    val userList: LiveData<List<User>>
        get() = repo.userList

    val createUser: LiveData<User>
        get() = repo.registerUser

}