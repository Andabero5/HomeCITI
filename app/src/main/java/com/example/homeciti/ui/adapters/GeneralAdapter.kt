package com.example.homeciti.ui.adapters

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.databinding.ItemHomeGeneralBinding
import com.squareup.picasso.Picasso

class GeneralAdapter(val context : Context): RecyclerView.Adapter<GeneralAdapter.GeneralViewHolder>(){

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

    inner class GeneralViewHolder(binding:ItemHomeGeneralBinding):RecyclerView.ViewHolder(binding.root){

        // Defino los elementos de la vista a traves del binding
        private val itemText = binding.itemText
        private val itemIcon = binding.itemIcon
        private val promoIcon = binding.itemLabeltag

        // Metodo para bindear la vista
        fun render(general: GeneralService){

            Picasso.get().load(general.icon).into(itemIcon)
            itemText.text = general.type

            // EL BACKGROUND DEPENDE DEL TEXTO Y EN CASO DE NO HABER BACKGROUND DEFINIDO
            // BACKGROUND POR DEFECTO

            if (general.promoIcon.isNullOrEmpty()){
                promoIcon.visibility = View.INVISIBLE
            } else{
                promoIcon.visibility = View.VISIBLE

                // Texto
                promoIcon.text = general.promoIcon

                // Color
                val drawable: GradientDrawable =  promoIcon.background as GradientDrawable
                if (!general.backgroundColor.isNullOrEmpty()) {
                    try {
                        drawable.setColor(general.backgroundColor.toColorInt())
                    } catch (e: Exception) {
                        drawable.setColor(Constants.COLOR_DEFAULT_BACKGROUND.toColorInt())
                    }
                } else drawable.setColor(Constants.COLOR_DEFAULT_BACKGROUND.toColorInt())
            }
        }
    }
}