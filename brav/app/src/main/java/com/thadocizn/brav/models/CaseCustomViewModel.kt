package com.thadocizn.brav.models

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thadocizn.brav.viewModel.CaseViewModel
import com.thadocizn.brav.viewModel.MediatorViewModel

/**
 * Created by charles on 18,August,2019
 */
@Suppress("UNCHECKED_CAST")
class CaseCustomViewModel (private val application: Application, private val case: Case? = null) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CaseViewModel(application) as T
    }
}
