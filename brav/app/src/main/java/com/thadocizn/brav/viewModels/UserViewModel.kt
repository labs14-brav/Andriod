package com.thadocizn.brav.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.thadocizn.brav.User
import com.thadocizn.brav.repo.UserRepository

/**
 * Created by charles on 24,July,2019
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repo:UserRepository

    init {
        repo = UserRepository()
    }

    val userList:LiveData<List<User>>
    get() = repo.userList
}