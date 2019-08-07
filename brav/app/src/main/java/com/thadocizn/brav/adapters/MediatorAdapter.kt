package com.thadocizn.brav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Mediator
import kotlinx.android.synthetic.main.list_item_mediator.view.*

/**
 * Created by charles on 04,August,2019
 */
class MediatorAdapter(private val list: ArrayList<Mediator>?) : RecyclerView.Adapter<MediatorAdapter.ViewHolder>() {
    class ViewHolder(private val container: View) : RecyclerView.ViewHolder(container) {

        fun bindMediator(mediator: Mediator) {

            with(container) {
                tvMediatorName.text = mediator.name
                tvMediatorSpec.text = mediator.specialization
                tvMediatorExperience.text = mediator.experience
                tvMediatorLang.text = mediator.language
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_mediator, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMediator(list?.get(position)!!)
    }
}