package com.thadocizn.brav.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thadocizn.brav.viewModels.MediatorViewModel
import com.thadocizn.brav.viewModels.UserViewModel

/**
 * Created by charles on 26,July,2019
 */
class MediatorCustomViewModel(private val token: String?) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MediatorViewModel(token) as T
    }
}