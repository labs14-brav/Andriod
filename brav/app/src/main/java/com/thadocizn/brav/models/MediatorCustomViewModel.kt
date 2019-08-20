package com.thadocizn.brav.models

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thadocizn.brav.viewModel.MediatorViewModel

/**
 * Created by charles on 18,August,2019
 */
@Suppress("UNCHECKED_CAST")
class MediatorCustomViewModel (private val application: Application, private val price: String, private val language: String, private val specialty: String, private val experience: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MediatorViewModel(application, price,language,specialty,experience) as T
    }
}