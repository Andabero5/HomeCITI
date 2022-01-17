package com.example.homeciti.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.R
import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.data.model.Service
import com.example.homeciti.databinding.ItemHomeGeneralBinding
import com.example.homeciti.databinding.ItemHomeQuickaccessBinding
import com.example.homeciti.ui.view.HomeFragment
import com.squareup.picasso.Picasso

class GeneralAdapter(val context : Context): RecyclerView.Adapter<GeneralAdapter.GeneralViewHolder>() {

    // Crear mi lista de datos mutable
    private var dataList = mutableListOf<GeneralService>()

    // Funcion para settear los nuevos datos
    fun setListData(data:MutableList<GeneralService>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemHomeGeneralBinding.inflate(inflater,parent,false)
        return GeneralViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GeneralViewHolder, position: Int) {
        val general = dataList[position]
        holder.render(general)
    }

    override fun getItemCount(): Int = dataList.size

    inner class GeneralViewHolder (private val binding:ItemHomeGeneralBinding):RecyclerView.ViewHolder(binding.root){

        // Defino los elementos de la vista a traves del binding
        private val itemText = binding.itemLabel
        private val itemIcon = binding.itemIcon
        private val promoIcon = binding.itemLabelTag

        // Metodo para bindear la vista
        fun render(general: GeneralService){

            Picasso.get().load(general.icon).into(itemIcon)
            itemText.text = general.type

            if (general.promoIcon == ""){
                promoIcon.visibility = View.INVISIBLE
            } else{
                promoIcon.visibility = View.VISIBLE

                if (general.backgroundColor == "red") {
                    promoIcon.setBackgroundResource(R.drawable.background_item_labeltag)
                }
                else if(general.backgroundColor == "blue") {
                    promoIcon.setBackgroundResource(R.drawable.background_item_labeltag_blue)
                }

                promoIcon.text = general.promoIcon
            }
        }
    }
}