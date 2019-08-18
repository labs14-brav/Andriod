package com.thadocizn.brav.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.repos.CaseRepo
import com.thadocizn.brav.repos.MediatorRepo

/**
 * Created by charles on 18,August,2019
 */
class MediatorViewModel(application: Application, price:String, language:String, specialty:String, experience:String) : AndroidViewModel(application) {


    val repo = MediatorRepo(application, price, language, specialty, experience)

    val getMediators: LiveData<List<Mediator>>
        get() =
            repo.caseList

    override fun onCleared() {
        super.onCleared()
        repo.job.cancel()
    }

}