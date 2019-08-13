package com.thadocizn.brav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_case_details.*
import kotlinx.android.synthetic.main.activity_courtcase_form.*

class CaseDetailsActivity : AppCompatActivity() {

    companion object{
        const val CASE_COMPLETE = "complete"
        const val CASE_DESCRIPTION = "description"
        const val CASE_CATEGORY = "category"
        const val CASE_AMOUNT = "amount"
        const val CASE_PARTIES_INVOLVED = "partiesInvolved"
        const val CASE_PARTIES_INFO = "partiesInfo"
        const val CASE_COURT = "courtCase"
        const val CASE_COURT_JURIS = "jurisdiction"
        const val CASE_COURT_NUMBER = "courtNumber"
        const val CASE_COURT_FILING= "filing"
        const val CASE_NOTES = "notes"
        const val CASE_ACCEPTED = "accepted"
        const val CASE_DECLINED = "declined"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_details)

        val getCase = intent.extras

        if (getCase != null){

            val completed = getCase.getString(CASE_COMPLETE)
            val description = getCase.getString(CASE_DESCRIPTION)
            val category = getCase.getString(CASE_CATEGORY)
            val amount = getCase.getString(CASE_AMOUNT)
            val involved = getCase.getString(CASE_PARTIES_INVOLVED)
            val info = getCase.getString(CASE_PARTIES_INFO)
            val caseCourt = getCase.getString(CASE_COURT)
            val jurisdiction = getCase.getString(CASE_COURT_JURIS)
            val courtNumber = getCase.getString(CASE_COURT_NUMBER)
            val filing = getCase.getString(CASE_COURT_FILING)
            val notes = getCase.getString(CASE_NOTES)
            val accepted = getCase.getString(CASE_ACCEPTED)
            val declined = getCase.getString(CASE_DECLINED)

            tvCaseDetailAmount.text = amount
            tvCaseDetailCategory.text = category
            tvCaseDetailCompleted.text = completed
            tvCaseDetailDesc.text = description
            tvCaseDetailPartiesInvolved.text = involved
            tvCaseDetailPartiesInfo.text = info
            //tvCourtJurisdiction.text = jurisdiction
            //tvCaseNum.text = courtNumber
            //tvCourtFilingDate.text = filing

        }
    }
}
