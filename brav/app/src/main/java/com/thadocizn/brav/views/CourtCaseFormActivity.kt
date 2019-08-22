package com.thadocizn.brav.views

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_courtcase_form.*
import kotlinx.coroutines.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourtCaseFormActivity : AppCompatActivity() {
    private lateinit var idToken: String
    val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courtcase_form)

        val sharedPreference = SharedPreference(this)

        idToken = sharedPreference.getToken("token").toString()

        setupSpinner()

        btnCourtCase.setOnClickListener {

            val case: Case = case()

            createCase(case)
            startActivity<CaseActivity>()
        }
    }

    private fun case(): Case {
        var caseId: Int? = null
        val caseAcceptedAt = null
        val caseCompletedAt = null
        val caseDeclinedAt = null
        val notes = etCaseNotes.text.toString()
        val courtCase = true
        val courtFilingDate = etCourtFilingDate.text.toString()
        val courtJurisdiction = etCourtJurisdiction.text.toString()
        val courtNumber = etCaseNum.text.toString()
        val description = etDescription.text.toString()
        val disputeAmount = etDisputeAmount.text.toString()
        val disputeCategory = spDisputeCategory.selectedItem.toString()
        val partiesContactInfo = etPartiesContactInfo.text.toString()
        val partiesInvolved = etPartiesInvolved.text.toString()

        val case: Case = Case(
            caseId,
            caseCompletedAt,
            description,
            disputeCategory,
            disputeAmount,
            partiesInvolved,
            partiesContactInfo,
            courtCase,
            courtJurisdiction,
            courtNumber,
            courtFilingDate,
            notes,
            caseAcceptedAt,
            caseDeclinedAt
        )
        return case
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.specialization,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spDisputeCategory.adapter = adapter
        }
    }

    private fun createCase(case: Case) {
        coroutineScope.launch {
            val service = RetroInstance().service(idToken)
            val call = service.postCaseAsync(case)
            withContext(Dispatchers.Main){

                call.await()
            }
        }
    }
}