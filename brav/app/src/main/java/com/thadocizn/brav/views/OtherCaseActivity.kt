package com.thadocizn.brav.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.services.RetroInstance
import kotlinx.android.synthetic.main.activity_other_case.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtherCaseActivity : AppCompatActivity() {

    private lateinit var idToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_case)
        val mUser = FirebaseAuth.getInstance().currentUser
        mUser!!.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    idToken = task.result!!.token.toString()

                } else {

                    // Handle error -> task.getException();

                }

            }
        btnSend.setOnClickListener {

            val caseAcceptedAt = ""
            val caseCompletedAt = ""
            val caseDeclinedAt = ""
            val notes = etCaseNotes.text.toString()
            val courtCase = false
            val courtFilingDate = ""
            val courtJurisdiction = ""
            val courtNumber = ""
            val description = etDescription.text.toString()
            val disputeAmount = etDisputeAmount.text.toString()
            val disputeCategory = etDisputeCategory.text.toString()
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

            createCase(case)

        }
    }

    //TOdo fix bug when I get in touch with a BE person
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
