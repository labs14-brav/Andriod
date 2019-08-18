package com.thadocizn.brav.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.repos.CaseRepo

/**
 * Created by charles on 18,August,2019
 */
class CaseViewModel(application: Application) : AndroidViewModel(application) {


    val repo = CaseRepo(application)

    val getCases: LiveData<List<Case>>
        get() =
            repo.caseList

    override fun onCleared() {
        super.onCleared()
        repo.job.cancel()
    }

}