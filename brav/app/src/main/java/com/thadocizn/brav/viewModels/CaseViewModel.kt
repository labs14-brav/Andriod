package com.thadocizn.brav.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.repo.CaseRepository

/**
 * Created by charles on 24,July,2019
 */
class CaseViewModel(val token: String?, val case:String) : ViewModel() {

    private val repo: CaseRepository

    init {
        repo = CaseRepository(token, case)
    }

    val caseList: LiveData<List<Case>>
        get() = repo.caseList

    val createCase: LiveData<Case>
        get() = repo.createCase
}