package com.thadocizn.brav.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.thadocizn.brav.R
import com.thadocizn.brav.adapters.MediatorAdapter
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.services.BravApi
import com.thadocizn.brav.services.RetroInstance
import kotlinx.android.synthetic.main.activity_mediator.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//todo data isnt being displayed
class MediatorActivity : AppCompatActivity() {
    var mediator: ArrayList<Mediator>? = null
    private lateinit var idToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediator)

        val mUser = FirebaseAuth.getInstance().currentUser
        mUser!!.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    idToken = task.result!!.token.toString()
                    getMediators(idToken)


                } else {

                    // Handle error -> task.getException();

                }
            }
        setupSpinners()

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

    private fun getMediators(token: String) {
        val service: BravApi = RetroInstance().service(token)
        val call = service.getMediators("", "", "", "")

        call.enqueue(object : Callback<List<Mediator>> {
            override fun onFailure(call: Call<List<Mediator>>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                println(t.message)
            }

            override fun onResponse(call: Call<List<Mediator>>, response: Response<List<Mediator>>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                mediator = response.body() as ArrayList<Mediator>?
                println(mediator?.toArray().toString())

                getRecycleView(mediator)

            }

        })
    }

    private fun getRecycleView(list: ArrayList<Mediator>?) {
        val adapter = MediatorAdapter(list)
        rvMediator.adapter = adapter
        rvMediator.layoutManager = LinearLayoutManager(this)
    }

}
