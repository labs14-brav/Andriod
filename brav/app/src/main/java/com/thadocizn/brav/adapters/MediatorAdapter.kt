package com.thadocizn.brav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thadocizn.brav.MediatorDetailActivity
import com.thadocizn.brav.views.UserAccountActivity
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Mediator
import kotlinx.android.synthetic.main.list_item_mediator.view.*
import org.jetbrains.anko.*

/**
 * Created by charles on 04,August,2019
 */
class MediatorAdapter(private val list: ArrayList<Mediator>?) : RecyclerView.Adapter<MediatorAdapter.ViewHolder>() {

    class ViewHolder(private val container: View) : RecyclerView.ViewHolder(container) {

        val tvMediatorId: TextView = container.tvMediatorId
        val tvMediatorName: TextView = container.tvMediatorName
        val tvMediatorSpec: TextView = container.tvMediatorSpec
        val tvMediatorExperience: TextView = container.tvMediatorExperience
        val tvMediatorLang: TextView = container.tvMediatorLang
        val btnConnect:Button = container.btnConnect
         fun bindMediator(mediator: Mediator?){
              with(container){

                  container.setOnClickListener {

                      if (mediator != null) {
                          context.startActivity<MediatorDetailActivity>(

                              MediatorDetailActivity.EXPERIENCE to mediator.experience,
                              MediatorDetailActivity.LANGUAGE to mediator.language,
                              MediatorDetailActivity.LICENSE to mediator.license,
                              MediatorDetailActivity.NAME to mediator.name,
                              MediatorDetailActivity.SPECIALIZATION to mediator.specialization,
                              MediatorDetailActivity.PROFESSIONAL_BIO to mediator.professional_bio,
                              MediatorDetailActivity.TYPE to mediator.type
                          )
                      }
                  }
              }
         }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_mediator, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val mediator:Mediator? = list?.get(position)
        holder.bindMediator(list?.get(position))
        holder.tvMediatorId.text = mediator?.id.toString()
        holder.tvMediatorName.text = mediator?.name.toString()
        holder.tvMediatorSpec.text = mediator?.specialization.toString()
        holder.tvMediatorExperience.text = mediator?.experience.toString()
        holder.tvMediatorLang.text = mediator?.language.toString()
        holder.btnConnect.setOnClickListener {
            it.context.alert {
                customView{
                    verticalLayout{
                        val label = textView{
                            text = " Enter case Id"
                        }

                        val caseID = editText{
                            hint = "CaseID"
                        }

                        positiveButton("Connect"){

                            context.startActivity<UserAccountActivity>("mediatorId" to mediator!!.id, "caseId" to caseID.text.toString().toInt())
                        }
                    }
                }

            }.show()
        }

    }

}