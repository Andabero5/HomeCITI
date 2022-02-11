package com.example.homeciti.ui.adapters

import android.graphics.ColorFilter
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeciti.R
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
            Glide.with(itemView.context).load(item.icon).fitCenter ().into(binding.itemIcon)
            binding.itemLabel.text = item.type
            if (item.promoIcon.isNotEmpty()){
                binding.itemLabelTag.text = item.promoIcon
                val drawable: GradientDrawable =  binding.itemLabelTag.background as GradientDrawable
                drawable.setColor(item.backgroundColor.toColorInt())
                binding.itemLabelTag.visibility= View.VISIBLE
            }
            else{
                binding.itemLabelTag.visibility = View.GONE
            }
        }
    }
}