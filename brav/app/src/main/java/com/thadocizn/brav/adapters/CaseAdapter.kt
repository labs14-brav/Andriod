package com.thadocizn.brav.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thadocizn.brav.InvoiceActivity
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.Invoice
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.SharedPreference
import com.thadocizn.brav.views.CaseDetailsActivity
import com.thadocizn.brav.views.MediatorActivity
import kotlinx.android.synthetic.main.list_item_case.view.*
import kotlinx.coroutines.*
import org.jetbrains.anko.startActivity

/**
 * Created by charles on 04,August,2019
 */
class CaseAdapter(private val list: List<Case>?) : RecyclerView.Adapter<CaseAdapter.ViewHolder>() {

    lateinit var context:Context
    lateinit var sharedPreference: SharedPreference
    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    class ViewHolder(private val container: View) : RecyclerView.ViewHolder(container) {

        val btnInvoice = container.btnViewInvoices
        fun bindCase(case: Case) {

            with(container) {
                if (case.court_case!!){
                    tvCaseId.text = context.getString(R.string.courtCase)
                }else{
                    tvCaseId.text = context.getString(R.string.other)
                }
                tvCaseType.text = case.dispute_category
                tvParticipants.text = case.parties_contact_info

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
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_case, parent, false)
        sharedPreference = SharedPreference(parent.context)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list!!.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val case = list?.get(position)
        holder.bindCase(list?.get(position)!!)
        holder.btnInvoice.setOnClickListener {
            getInvoices(case?.id)
        }

    }

    private fun getInvoices(id: Int?) {

       val token = sharedPreference.getToken("token")
        coroutineScope.launch {
            val service = RetroInstance().service(token)
            val call = service.getInvoicesCaseIdAsync(1)

            withContext(Dispatchers.Main){
                val response = call.await()
                val mediator = response.mediator
                val list = response.invoice as MutableList<Invoice>
                context.startActivity<InvoiceActivity>("mediator" to mediator, "invoices" to list)
            }
        }

    }
}