package com.thadocizn.brav.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thadocizn.brav.viewModels.CaseViewModel

/**
 * Created by charles on 26,July,2019
 */
class CaseCustomViewModel(private val token: String?, private val case:String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CaseViewModel(token, case) as T
    }
}