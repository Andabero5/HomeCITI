package com.example.homeciti.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.R
import com.example.homeciti.data.model.BannerService
import com.example.homeciti.databinding.ItemHomeBannerBinding
import com.squareup.picasso.Picasso

class BannerAdapter(val context : Context): RecyclerView.Adapter<BannerAdapter.BannerViewHolder>(){

    // Crear mi lista de datos mutable
    private var dataList = mutableListOf<BannerService>()

    // Funcion para settear los nuevos datos
    fun setListData(data:MutableList<BannerService>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemHomeBannerBinding.inflate(inflater,parent,false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = dataList[position]
        holder.render(banner)
    }

    override fun getItemCount(): Int = dataList.size

    inner class BannerViewHolder(private val binding: ItemHomeBannerBinding):RecyclerView.ViewHolder(binding.root) {

        // Defino los elementos de la vista a traves del binding
        private val itemIcon = binding.itemIcon
        private val promoIcon = binding.itemLabelTag

        // Metodo para bindear la vista
        fun render(banner: BannerService){

            Picasso.get().load(banner.icon).into(itemIcon)

            if (banner.promoIcon == ""){
                promoIcon.visibility = View.INVISIBLE
            } else{
                promoIcon.visibility = View.VISIBLE

                if (banner.backgroundColor == "red") {
                    promoIcon.setBackgroundResource(R.drawable.background_item_labeltag)
                }
                else if(banner.backgroundColor == "blue") {
                    promoIcon.setBackgroundResource(R.drawable.background_item_labeltag_blue)
                }

                promoIcon.text = banner.promoIcon
            }
        }
    }
}