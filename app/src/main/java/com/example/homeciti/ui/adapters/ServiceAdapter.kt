package com.example.homeciti.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.R
import com.example.homeciti.data.model.Service
import com.example.homeciti.databinding.ItemHomeQuickaccessBinding
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class ServiceAdapter(val service:List<Service>, val context: Context): RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>(){

    private var dataList = mutableListOf<Service>()

    fun setListData(data:MutableList<Service>){
        dataList = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemHomeQuickaccessBinding.inflate(inflater,parent,false)
        //val layoutInflater = LayoutInflater.from(context).inflate(R.layout.item_home_quickaccess, parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = dataList[position]
        //holder.render(service[position])
        holder.render(service)
    }

    override fun getItemCount():
            //Int = service.size
            Int = dataList.size

    // Este es mi clase holder
    //inner class ServiceViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    inner class ServiceViewHolder(private val binding:ItemHomeQuickaccessBinding):RecyclerView.ViewHolder(binding.root){

        //val item_text = itemView.findViewById<TextView>(R.id.item_label)
        //val item_icon = itemView.findViewById<ImageView>(R.id.item_icon)
        //val promo_icon = itemView.findViewById<TextView>(R.id.item_labeltag)

        private val itemText = binding.itemLabel
        private val itemIcon = binding.itemIcon
        private val promoIcon = binding.itemLabeltag

        // Metodo para bindear la vista
        fun render(service: Service){

            Picasso.get().load(service.icon).into(itemIcon)
            itemText.text = service.type

            if (service.promoIcon == ""){
                promoIcon.visibility = View.INVISIBLE
            } else{
                promoIcon.visibility = View.VISIBLE

                if (service.backgroundColor == "red") {
                    promoIcon.setBackgroundResource(R.drawable.background_item_labeltag)
                }
                else if(service.backgroundColor == "blue") {
                    promoIcon.setBackgroundResource(R.drawable.background_item_labeltag_blue)
                }

                promoIcon.text = service.promoIcon
            }


        }
    }

}

