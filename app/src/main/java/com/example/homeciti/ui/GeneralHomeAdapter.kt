package com.example.homeciti.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.databinding.ItemHomeGeneralBinding

class GeneralHomeAdapter(private val data:List<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeGeneralBinding.inflate(inflater,parent,false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HomeViewHolder -> holder.bind(data[position])
        }
    }

    override fun getItemCount(): Int =data.size

    private inner class HomeViewHolder(private val binding:ItemHomeGeneralBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item : String){
        }
    }
}