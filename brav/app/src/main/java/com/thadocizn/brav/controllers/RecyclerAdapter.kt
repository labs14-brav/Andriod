package com.thadocizn.brav.controllers

import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Case
import kotlinx.android.synthetic.main.list_adapter_layout.view.*

class RecyclerAdapter(private val cases:ArrayList<Case>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView = parent.inflate(R.layout.list_adapter_layout,false)
        return CaseHolder(inflatedView)
     }

    override fun getItemCount() = cases.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var data = cases[position]
        holder.itemView.case_title.text = data.title
        holder.itemView.case_description.text = data.info
        holder.itemView.case_viewed.text = data.cat

//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
class CaseHolder(v:View):RecyclerView.ViewHolder(v),View.OnClickListener{
    init{v.setOnClickListener(this)}

    override fun onClick(v: View?) {
        Log.d("RecyclerView","CLICK!")
    }
    companion object{
        private val CASE_KEY = "CASE"
    }
}