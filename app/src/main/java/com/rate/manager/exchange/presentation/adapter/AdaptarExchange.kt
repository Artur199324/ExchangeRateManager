package com.rate.manager.exchange.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rate.manager.exchange.R
import com.rate.manager.exchange.domain.models.ExchangeRatesModels

class AdaptarExchange(val context: Context,val arrayList: ArrayList<ExchangeRatesModels>):
    RecyclerView.Adapter<AdaptarExchange.ViewHolder>() {



    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var tex1 = itemView.findViewById<TextView>(R.id.textView)
        var tex2 = itemView.findViewById<TextView>(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapte_rec,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val model = arrayList[position]
        holder.tex1.text = model.can
        holder.tex2.text = model.num
    }
}