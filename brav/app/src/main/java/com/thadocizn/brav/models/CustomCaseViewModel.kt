package com.thadocizn.brav.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thadocizn.brav.viewModels.CaseViewModel

/**
 * Created by charles on 26,July,2019
 */
class CustomCaseViewModel(private val token: String?) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CaseViewModel(token) as T
    }
}