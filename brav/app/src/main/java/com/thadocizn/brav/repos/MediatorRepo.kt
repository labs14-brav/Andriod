package com.thadocizn.brav.repos

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.SharedPreference
import kotlinx.coroutines.*
import retrofit2.HttpException

/**
 * Created by charles on 18,August,2019
 */
class MediatorRepo(application: Application, val price:String, val language:String, val specialty:String, val experience:String) {

    private var mediators = mutableListOf<Mediator>()
    private val mutableLiveData = MutableLiveData<List<Mediator>>()
    val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)
    val token = SharedPreference(application.applicationContext).getToken("token").toString()

    val caseList: MutableLiveData<List<Mediator>>
        get() {
            coroutineScope.launch {
                val service = RetroInstance().service(token)
                val call = service.getMediatorsAsync(price,language,specialty, experience)

                withContext(Dispatchers.Main) {

                    try {
                        val response = call.await()
                        mediators = response as MutableList<Mediator>
                        mutableLiveData.value = mediators
                    } catch (e: HttpException) {
                    }
                }
            }

            return mutableLiveData
        }

}