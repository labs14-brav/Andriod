package com.thadocizn.brav.views

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.thadocizn.brav.R
import com.thadocizn.brav.adapters.MediatorAdapter
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.models.MediatorCustomViewModel
import com.thadocizn.brav.services.BravApi
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.SharedPreference
import com.thadocizn.brav.viewModel.MediatorViewModel
import kotlinx.android.synthetic.main.activity_mediator.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MediatorActivity : AppCompatActivity() {
    private lateinit var idToken: String
    var caseId: Int? = 0
    lateinit var viewModel:MediatorViewModel

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
        viewModel = ViewModelProviders.of(this,MediatorCustomViewModel(application,price,language,specialty,experience)).get(MediatorViewModel::class.java)

        viewModel.getMediators.observe(this, Observer { mediatorList ->

            getRecycleView(mediatorList)
        })

    }

    private fun getRecycleView(list: List<Mediator>?) {
        val adapter = MediatorAdapter(list, caseId)
        rvMediator.adapter = adapter
        rvMediator.layoutManager = GridLayoutManager(this, 2)
    }

}
