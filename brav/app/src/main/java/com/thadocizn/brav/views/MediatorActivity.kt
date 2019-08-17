package com.thadocizn.brav.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.thadocizn.brav.R
import com.thadocizn.brav.adapters.MediatorAdapter
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.services.BravApi
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_mediator.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MediatorActivity : AppCompatActivity() {
    var mediator: ArrayList<Mediator>? = null
    private lateinit var idToken: String
    var caseId:Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediator)
        val sharedPreference = SharedPreference(this)

        idToken = sharedPreference.getToken("token").toString()
        caseId = intent.getIntExtra("caseId", 0)

        setupSpinners()
        getMediators()

        btnSearch.setOnClickListener {

           getMediators()

        }

    }

    private fun setupSpinners() {

        ArrayAdapter.createFromResource(
            this,
            R.array.price,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spPrice.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.language,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spLanguage.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.specialization,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spSpecialty.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.experience,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spExperience.adapter = adapter
        }

    }

    private fun getMediators() {
        val price: String = spPrice.selectedItem.toString()
        val language = spLanguage.selectedItem.toString()
        val specialty = spSpecialty.selectedItem.toString()
        val experience = spExperience.selectedItem.toString()

        val service: BravApi = RetroInstance().service(idToken)
        val call = service.getMediators(price, experience, specialty, language)

        call.enqueue(object : Callback<List<Mediator>> {
            override fun onFailure(call: Call<List<Mediator>>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                println(t.message)
            }

            override fun onResponse(call: Call<List<Mediator>>, response: Response<List<Mediator>>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                mediator = response.body() as ArrayList<Mediator>?

                println(mediator.toString())
                getRecycleView(mediator)

            }

        })
    }

    private fun getRecycleView(list: ArrayList<Mediator>?) {
        val adapter = MediatorAdapter(list, caseId)
        rvMediator.adapter = adapter
        rvMediator.layoutManager = GridLayoutManager(this, 2)
    }

}
