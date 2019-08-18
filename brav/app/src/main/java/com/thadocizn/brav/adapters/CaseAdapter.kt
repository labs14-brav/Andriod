package com.thadocizn.brav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.views.CaseDetailsActivity
import com.thadocizn.brav.views.MediatorActivity
import kotlinx.android.synthetic.main.list_item_case.view.*
import org.jetbrains.anko.startActivity

/**
 * Created by charles on 04,August,2019
 */
class CaseAdapter(private val list: List<Case>?) : RecyclerView.Adapter<CaseAdapter.ViewHolder>() {
    class ViewHolder(private val container: View) : RecyclerView.ViewHolder(container) {

        fun bindCase(case: Case) {

            with(container) {
                tvCaseId.text = case.id.toString()
                tvCaseType.text = case.dispute_category
                tvCaseAmount.text = case.dispute_amount
                tvCaseNote.text = case.case_notes
                container.setOnClickListener {
                    context.startActivity<CaseDetailsActivity>(
                        CaseDetailsActivity.CASE_ACCEPTED to case.case_accepted_at,
                        CaseDetailsActivity.CASE_AMOUNT to case.dispute_amount,
                        CaseDetailsActivity.CASE_CATEGORY to case.dispute_category,
                        CaseDetailsActivity.CASE_COMPLETE to case.case_completed_at,
                        CaseDetailsActivity.CASE_COURT to case.court_case,
                        CaseDetailsActivity.CASE_COURT_FILING to case.court_filing_date,
                        CaseDetailsActivity.CASE_COURT_JURIS to case.court_jurisdiction,
                        CaseDetailsActivity.CASE_COURT_NUMBER to case.court_number,
                        CaseDetailsActivity.CASE_DECLINED to case.case_declined_at,
                        CaseDetailsActivity.CASE_DESCRIPTION to case.description,
                        CaseDetailsActivity.CASE_PARTIES_INFO to case.parties_contact_info,
                        CaseDetailsActivity.CASE_PARTIES_INVOLVED to case.parties_involved,
                        CaseDetailsActivity.CASE_NOTES to case.case_notes
                    )
                }
                btnFindMediator.setOnClickListener {

                    context.startActivity<MediatorActivity>("caseId" to case.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_case, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list!!.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCase(list?.get(position)!!)
    }
}