package com.example.homeciti.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.data.model.Service
import com.example.homeciti.databinding.ItemHomeGeneralBinding


class GeneralHomeAdapter(private val service:List<Service>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeGeneralBinding.inflate(inflater,parent,false)
        return GeneralHomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is GeneralHomeViewHolder -> holder.bind(service[position])
        }
    }

    override fun getItemCount(): Int =service.size

    private inner class GeneralHomeViewHolder(private val binding:ItemHomeGeneralBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item : Service){
            binding.itemText.text = item.type
            if (item.promoIcon.isNotEmpty()){
                binding.itemLabeltag.text = item.promoIcon
            }else{
                binding.itemLabeltag.visibility = View.INVISIBLE
            }
//            item.backgroundColor.let {
//                binding.cvItemGeneral.setCardBackgroundColor(it.toColorInt())
//            }
        }
    }
}