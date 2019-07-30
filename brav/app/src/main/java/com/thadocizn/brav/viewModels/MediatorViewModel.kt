package com.thadocizn.brav.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.models.User
import com.thadocizn.brav.repo.MediatorRepository
import com.thadocizn.brav.repo.UserRepository

/**
 * Created by charles on 24,July,2019
 */
class MediatorViewModel(val token: String?) : ViewModel() {

    private val repo: MediatorRepository

    init {
        repo = MediatorRepository(token)
    }

    val userList: LiveData<List<Mediator>>
        get() = repo.mediatorList


}