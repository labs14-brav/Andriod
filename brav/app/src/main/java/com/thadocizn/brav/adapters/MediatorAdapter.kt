package com.thadocizn.brav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.thadocizn.brav.Main2Activity
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.views.CaseActivity
import com.thadocizn.brav.views.MediatorActivity
import kotlinx.android.synthetic.main.list_item_mediator.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_mediator, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val mediator:Mediator? = list?.get(position)
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

                            context.startActivity<Main2Activity>("mediatorId" to mediator!!.id, "caseId" to caseID.text.toString().toInt())
                        }
                    }
                }

            }.show()
        }
    }

}