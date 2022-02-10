package com.example.homeciti.ui.adapters

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.data.model.QuickAccessService
import com.example.homeciti.databinding.ItemHomeQuickaccessBinding
import com.squareup.picasso.Picasso

class QuickAccessAdapter(val context : Context): RecyclerView.Adapter<QuickAccessAdapter.ServiceViewHolder>(){

    // Crear mi lista de datos mutable
    private var dataList = mutableListOf<QuickAccessService>()

    // Funcion para settear los nuevos datos
    fun setListData(data:MutableList<QuickAccessService>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemHomeQuickaccessBinding.inflate(inflater,parent,false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = dataList[position]
        holder.render(service)
    }

    override fun getItemCount(): Int = dataList.size

    inner class ServiceViewHolder(binding:ItemHomeQuickaccessBinding):RecyclerView.ViewHolder(binding.root){

        // Defino los elementos de la vista a traves del binding
        private val itemText = binding.itemLabel
        private val itemIcon = binding.itemIcon
        private val promoIcon = binding.itemLabeltag

        // Metodo para bindear la vista
        fun render(quickAccessService: QuickAccessService){

            Picasso.get().load(quickAccessService.icon).into(itemIcon)
            itemText.text = quickAccessService.type

            if (quickAccessService.promoIcon.isNullOrEmpty()){
                promoIcon.visibility = View.INVISIBLE
            } else{
                promoIcon.visibility = View.VISIBLE

                val drawable: GradientDrawable =  promoIcon.background as GradientDrawable
                drawable.setColor(quickAccessService.backgroundColor.toColorInt())

                promoIcon.text = quickAccessService.promoIcon
            }
        }
    }
}