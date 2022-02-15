package com.example.homeciti.ui.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.data.model.BannerService
import com.example.homeciti.databinding.ItemHomeBannerBinding
import com.squareup.picasso.Picasso

class BannerAdapter(val context : Context): RecyclerView.Adapter<BannerAdapter.BannerViewHolder>(){

    // Crear mi lista de datos mutable
    private var dataList = mutableListOf<BannerService>()

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

    override fun getItemCount(): Int{
        return dataList.size
    }

    inner class BannerViewHolder(binding: ItemHomeBannerBinding):RecyclerView.ViewHolder(binding.root) {

        // Defino los elementos de la vista a traves del binding
        private val itemIcon = binding.itemIcon
        private val promoIcon = binding.itemLabelTag

        // Metodo para bindear la vista
        fun render(banner: BannerService){

            Picasso.get().load(banner.icon).into(itemIcon)



            /*
            // EL BACKGROUND DEPENDE DEL TEXTO Y EN CASO DE NO HABER BACKGROUND DEFINIDO
            // BACKGROUND NO EXISTE
            if (banner.promoIcon.isNullOrEmpty()){
                promoIcon.visibility = View.INVISIBLE
            } else{
                promoIcon.visibility = View.VISIBLE

                if(!(banner.backgroundColor.isNullOrEmpty())){
                    val drawable: GradientDrawable =  promoIcon.background as GradientDrawable
                    drawable.setColor(banner.backgroundColor.toColorInt())
                    //general.backgroundColor?.let { drawable.setColor(it.toColorInt()) }
                }else{
                    promoIcon.setBackgroundColor(Color.TRANSPARENT)
                }

                promoIcon.text = banner.promoIcon
            }

             */


            // EL BACKGROUND DEPENDE DEL TEXTO Y EN CASO DE NO HABER BACKGROUND DEFINIDO
            // BACKGROUND POR DEFECTO

            if (banner.promoIcon.isNullOrEmpty()){
                promoIcon.visibility = View.INVISIBLE
            } else{
                promoIcon.visibility = View.VISIBLE

                promoIcon.text = banner.promoIcon

                val drawable: GradientDrawable =  promoIcon.background as GradientDrawable
                banner.backgroundColor?.let { drawable.setColor(it.toColorInt()) }
            }

            /*
            // TEXTO Y BACKGROUND SON INDEPENDIENTES
            if(!(banner.promoIcon.isNullOrEmpty())){
                promoIcon.text = banner.promoIcon
            }

            if(!(banner.backgroundColor.isNullOrEmpty())){
                val drawable: GradientDrawable =  promoIcon.background as GradientDrawable
                drawable.setColor(banner.backgroundColor.toColorInt())
                //general.backgroundColor?.let { drawable.setColor(it.toColorInt()) }
            }else{
                promoIcon.setBackgroundColor(Color.TRANSPARENT)
            }
             */
        }
    }
}