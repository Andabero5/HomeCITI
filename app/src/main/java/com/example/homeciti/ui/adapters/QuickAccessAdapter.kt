package com.example.homeciti.ui.adapters

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeciti.data.model.QuickAccessService
import com.example.homeciti.databinding.ItemHomeQuickaccessBinding

class QuickAccessAdapter (private val quickAccessService:List<QuickAccessService>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeQuickaccessBinding.inflate(inflater,parent,false)
        return QuickAccessViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is QuickAccessViewHolder -> holder.bind(quickAccessService[position])
        }
    }

    override fun getItemCount(): Int = quickAccessService.size

    private inner class QuickAccessViewHolder(private val binding: ItemHomeQuickaccessBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item : QuickAccessService){
            Glide.with(itemView.context).load(item.icon).fitCenter().into(binding.itemIcon)
            binding.itemLabel.text = item.type
            if (item.promoIcon.isNotEmpty()){
                binding.itemLabelTag.text = item.promoIcon
                val drawable: GradientDrawable =  binding.itemLabelTag.background as GradientDrawable
                drawable.setColor(item.backgroundColor.toColorInt())
                binding.itemLabelTag.visibility= View.VISIBLE
            }
        }
    }
}