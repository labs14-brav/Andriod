package com.thadocizn.brav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thadocizn.brav.PaymentActivity
import com.thadocizn.brav.R
import com.thadocizn.brav.adapters.InvoiceAdapter.*
import com.thadocizn.brav.models.Invoice
import com.thadocizn.brav.models.Mediator
import kotlinx.android.synthetic.main.list_item_invoices.view.*
import org.jetbrains.anko.startActivity

/**
 * Created by charles on 24,August,2019
 */
class InvoiceAdapter(val list: List<Invoice>, val mediator: Mediator):RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_invoices, parent, false)
        return ViewHolder(view,mediator)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindInvoice(list.get(position), mediator)
    }

    class ViewHolder(val container:View, val mediator: Mediator):RecyclerView.ViewHolder(container) {

        fun bindInvoice(invoice: Invoice, mediator: Mediator){

            with(container){


                tvInvoiceAmount.text = invoice.amount.toString()
                tvInvoiceCaseId.text = invoice.caseId.toString()
                tvInvoiceHours.text  = invoice.hours.toString()
                tvInvoiceMediatorId.text = invoice.mediatorId.toString()

                when(invoice.paidAt){
                    null -> {
                        btnPay.text = "Pay"
                        btnPay.setOnClickListener {
                            context.startActivity<PaymentActivity>("caseId" to invoice.caseId)
                        }
                    }
                    else -> btnPay.isEnabled = false
                }

            }

        }
    }
}