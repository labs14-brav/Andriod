package com.thadocizn.brav.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.services.RetroInstance
import kotlinx.android.synthetic.main.activity_courtcase_form.*
import kotlinx.android.synthetic.main.activity_courtcase_form.etCaseNotes
import kotlinx.android.synthetic.main.activity_courtcase_form.etDescription
import kotlinx.android.synthetic.main.activity_courtcase_form.etDisputeAmount
import kotlinx.android.synthetic.main.activity_courtcase_form.etPartiesContactInfo
import kotlinx.android.synthetic.main.activity_courtcase_form.etPartiesInvolved
import kotlinx.android.synthetic.main.activity_courtcase_form.spDisputeCategory
import kotlinx.android.synthetic.main.activity_other_case.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourtCaseFormActivity : AppCompatActivity() {
    private lateinit var idToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courtcase_form)
        val mUser = FirebaseAuth.getInstance().currentUser
        mUser!!.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    idToken = task.result!!.token.toString()


                } else {

                    // Handle error -> task.getException();

                }
            }

        setupSpinner()

        btnCourtCase.setOnClickListener {

            val case: Case = case()

            createCase(case)

        }
    }

    private fun case(): Case {
        val caseAcceptedAt = etCaseAccept.text.toString()
        val caseCompletedAt = etCaseComplete.text.toString()
        val caseDeclinedAt = etCaseDecline.text.toString()
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
        val service = RetroInstance().service(idToken)
        val call = service.postCase(case)

        call.enqueue(object : Callback<Case> {
            override fun onFailure(call: Call<Case>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                println(t.message)
            }

            override fun onResponse(call: Call<Case>, response: Response<Case>) {
                //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                println(response.body().toString())
            }
        })
    }
}