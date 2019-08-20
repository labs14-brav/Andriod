package com.thadocizn.brav.repos

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.SharedPreference
import kotlinx.coroutines.*
import retrofit2.HttpException

/**
 * Created by charles on 18,August,2019
 */
class CaseRepo(application: Application) {

    private var cases = mutableListOf<Case>()
    private val mutableLiveDataAllCases = MutableLiveData<List<Case>>()
    val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)
    val token = SharedPreference(application.applicationContext).getToken("token").toString()

    val caseList: MutableLiveData<List<Case>>
        get() {
            coroutineScope.launch {
                val service = RetroInstance().service(token)
                val call = service.getCasesAsync()

                withContext(Dispatchers.Main) {

                    try {
                        val response = call.await()
                        cases = response as MutableList<Case>
                        mutableLiveDataAllCases.value = cases
                    } catch (e: HttpException) {
                    }
                }
            }

            return mutableLiveDataAllCases
        }

}