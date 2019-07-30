package com.thadocizn.brav.repo

import androidx.lifecycle.MutableLiveData
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.services.BravApi
import com.thadocizn.brav.services.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by charles on 24,July,2019
 */
class CaseRepository(private val token: String?, private val case:String) {

    private var cases: ArrayList<Case> = ArrayList()
    private val mutableLiveData = MutableLiveData<List<Case>>()
    private val createCaseMutableLiveData = MutableLiveData<Case>()

    val caseList: MutableLiveData<List<Case>>
        get() {
            val service: BravApi = RetroInstance().service(token)
            val call = service.getCases()

            call.enqueue(object : Callback<List<Case>> {
                override fun onResponse(call: Call<List<Case>>, response: Response<List<Case>>) {
                    response.body()?.let { cases.addAll(it) }
                    println(response.isSuccessful)
                    mutableLiveData.postValue(cases)
                }

                override fun onFailure(call: Call<List<Case>>, t: Throwable) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    println(t.message)
                }

            })

            return mutableLiveData
        }

    val createCase: MutableLiveData<Case>
        get() {
            val service: BravApi = RetroInstance().service(token)
            val call = service.createCase(case)


            call.enqueue(object : Callback<Case> {
                override fun onFailure(call: Call<Case>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(call: Call<Case>, response: Response<Case>) {
                    val jsonResponse: Case? = response.body()
                    createCaseMutableLiveData.postValue(jsonResponse)
                }

            })
            return createCaseMutableLiveData
        }
}