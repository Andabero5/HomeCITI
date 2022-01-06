package com.example.homeciti.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.databinding.ItemHomeGeneralBinding


class GeneralAdapter(private val generalService:List<GeneralService>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeGeneralBinding.inflate(inflater,parent,false)
        return GeneralHomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is GeneralHomeViewHolder -> holder.bind(generalService[position])
        }
    }

    override fun getItemCount(): Int =generalService.size

    private inner class GeneralHomeViewHolder(private val binding:ItemHomeGeneralBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item : GeneralService){
            //Glide.with(itemView.context).load(item.icon).fitCenter().into(binding.itemIcon)
            binding.itemText.text = item.type
            if (item.promoIcon.isNotEmpty()){
                binding.itemLabeltag.text = item.promoIcon
                binding.itemLabeltag.visibility= View.VISIBLE
            }
        }
    }
}