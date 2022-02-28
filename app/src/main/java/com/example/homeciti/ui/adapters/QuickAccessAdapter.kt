package com.example.homeciti.ui.adapters

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.core.Constants
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
        val quickaccess = dataList[position]
        holder.render(quickaccess)
    }

    override fun getItemCount(): Int = dataList.size

    inner class ServiceViewHolder(binding:ItemHomeQuickaccessBinding):RecyclerView.ViewHolder(binding.root){

        // Defino los elementos de la vista a traves del binding
        private val itemText = binding.itemLabel
        private val itemIcon = binding.itemIcon
        private val promoIcon = binding.itemLabeltag

        // Metodo para bindear la vista
        fun render(quickAccess: QuickAccessService){

            Picasso.get().load(quickAccess.icon).into(itemIcon)
            itemText.text = quickAccess.type

            /*
            // TEXTO Y BACKGROUND SON INDEPENDIENTES
            if(!(quickAccess.promoIcon.isNullOrEmpty())){
                promoIcon.text = quickAccess.promoIcon
            }

            if(!(quickAccess.backgroundColor.isNullOrEmpty())){
                val drawable: GradientDrawable =  promoIcon.background as GradientDrawable
                drawable.setColor(quickAccess.backgroundColor.toColorInt())
                //general.backgroundColor?.let { drawable.setColor(it.toColorInt()) }
            }else{
                promoIcon.setBackgroundColor(Color.TRANSPARENT)
            }

             */

            /*
            // EL BACKGROUND DEPENDE DE SI HAY TEXTO

            if (quickAccess.promoIcon.isNullOrEmpty()){
                promoIcon.visibility = View.INVISIBLE
            } else{
                promoIcon.visibility = View.VISIBLE

                val drawable: GradientDrawable =  promoIcon.background as GradientDrawable
                drawable.setColor(quickAccess.backgroundColor.toColorInt())

                promoIcon.text = quickAccess.promoIcon
            }

             */
            // EL BACKGROUND DEPENDE DEL TEXTO Y EN CASO DE NO HABER BACKGROUND DEFINIDO
            // BACKGROUND POR DEFECTO

            if (quickAccess.promoIcon.isNullOrEmpty()){
                promoIcon.visibility = View.INVISIBLE
            } else{
                promoIcon.visibility = View.VISIBLE

                // Texto
                promoIcon.text = quickAccess.promoIcon

                // Color
                val drawable: GradientDrawable =  promoIcon.background as GradientDrawable
                if (!quickAccess.backgroundColor.isNullOrEmpty()) {
                    try {
                        drawable.setColor(quickAccess.backgroundColor.toColorInt())
                    } catch (e: Exception) {
                        drawable.setColor(Constants.COLOR_DEFAULT_BACKGROUND.toColorInt())
                    }
                } else drawable.setColor(Constants.COLOR_DEFAULT_BACKGROUND.toColorInt())

            }
        }
    }
}