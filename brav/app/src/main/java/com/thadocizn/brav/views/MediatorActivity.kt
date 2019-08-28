package com.thadocizn.brav.views

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.thadocizn.brav.R
import com.thadocizn.brav.adapters.MediatorAdapter
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_mediator.*
import kotlinx.coroutines.*


class MediatorActivity : AppCompatActivity() {
    private lateinit var idToken: String
    var caseId: Int? = 0
    val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

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

        coroutineScope.launch {
            val service = RetroInstance().service(idToken)
            val  call = service.getMediatorsAsync(price,experience,specialty,language)

            withContext(Dispatchers.Main){
                val response = call.await()
                val list = response
                getRecycleView(list)

            }
        }

    }

    private fun getRecycleView(list: List<Mediator>?) {
        val adapter = MediatorAdapter(list, caseId)
        rvMediator.adapter = adapter
        rvMediator.layoutManager = GridLayoutManager(this, 2)
    }

}
