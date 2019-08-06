package com.thadocizn.brav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Case
import kotlinx.android.synthetic.main.list_item_case.view.*

/**
 * Created by charles on 04,August,2019
 */
class CaseAdapter(private val list: ArrayList<Case>?) : RecyclerView.Adapter<CaseAdapter.ViewHolder>() {
    class ViewHolder(private val container: View) : RecyclerView.ViewHolder(container) {

        fun bindCase(case: Case) {

            with(container) {
                tvCaseDesc.text = case.dispute_category
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_case, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size!!


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCase(list?.get(position)!!)
    }
}