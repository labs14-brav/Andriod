package com.thadocizn.brav.repo

import androidx.lifecycle.MutableLiveData
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.services.BravApi
import com.thadocizn.brav.services.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by charles on 24,July,2019
 */
class MediatorRepository(private val token: String?) {

    private var mediators: ArrayList<Mediator> = ArrayList()
    private val mutableLiveData = MutableLiveData<List<Mediator>>()

    val mediatorList: MutableLiveData<List<Mediator>>
        get() {
            val service: BravApi = RetroInstance().service(token)
            val call = service.getMediators()

            call.enqueue(object : Callback<List<Mediator>> {
                override fun onResponse(call: Call<List<Mediator>>, response: Response<List<Mediator>>) {
                    response.body()?.let { mediators.addAll(it) }
                    println(response.isSuccessful)
                    mutableLiveData.postValue(mediators)
                }

                override fun onFailure(call: Call<List<Mediator>>, t: Throwable) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    println(t.message)
                }

            })

            return mutableLiveData
        }

}