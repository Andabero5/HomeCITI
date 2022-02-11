package com.example.homeciti.ui.adapters

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeciti.data.model.BannerService
import com.example.homeciti.databinding.ItemHomeBannerBinding

class BannerAdapter (private val bannerService:List<BannerService>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeBannerBinding.inflate(inflater,parent,false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is BannerViewHolder -> holder.bind(bannerService[position])
        }
    }

    override fun getItemCount(): Int = bannerService.size

    private inner class BannerViewHolder(private val binding: ItemHomeBannerBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item : BannerService){
            Glide.with(itemView.context).load(item.icon).fitCenter().into(binding.itemIcon)
            if (item.promoIcon.isNotEmpty()){
                binding.itemLabelTag.text = item.promoIcon
                val drawable: GradientDrawable =  binding.itemLabelTag.background as GradientDrawable
                drawable.setColor(item.backgroundColor.toColorInt())
                binding.itemLabelTag.visibility= View.VISIBLE
            }
        }
    }
}