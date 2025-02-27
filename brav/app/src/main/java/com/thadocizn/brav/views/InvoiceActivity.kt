package com.thadocizn.brav.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.thadocizn.brav.R
import com.thadocizn.brav.adapters.InvoiceAdapter
import com.thadocizn.brav.models.Invoice
import com.thadocizn.brav.models.Mediator
import kotlinx.android.synthetic.main.activity_invoice.*
import org.jetbrains.anko.toast

class InvoiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)

        val mediator = intent.extras.getParcelable("mediator") as Mediator
       val list = intent.extras.getStringArrayList("invoices") as List<Invoice>

        when(list){
            null -> toast("Nothing to show")
            else -> displayInvoices(list,mediator)
        }

    }

    private fun displayInvoices(
        list: List<Invoice>,
        mediator: Mediator
    ) {
        val adapter = InvoiceAdapter(list, mediator)
        rvInvoice.adapter = adapter
        rvInvoice.layoutManager = GridLayoutManager(this, 2)
    }
}
