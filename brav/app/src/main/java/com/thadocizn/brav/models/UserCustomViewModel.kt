package com.thadocizn.brav.models

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thadocizn.brav.viewModels.UserViewModel

/**
 * Created by charles on 26,July,2019
 */
class UserCustomViewModel(private val token: String?, private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(token, context) as T
    }
}