package com.example.homeciti.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.R
import com.example.homeciti.data.model.Service
import com.example.homeciti.databinding.ItemHomeQuickaccessBinding
import com.example.homeciti.ui.view.HomeFragment
import com.squareup.picasso.Picasso

// anteriormente aqui traia la lista como parametro ServiceAdapter(private val service : List<Service>)
class ServiceAdapter(val context: Context): RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>(){

    // Crear mi lista de datos mutable
    private var dataList = mutableListOf<Service>()

    // Funcion para settear los nuevos datos
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
        return ServiceViewHolder(binding, mListener)
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
    inner class ServiceViewHolder(private val binding:ItemHomeQuickaccessBinding, listener: onServiceClickListener):RecyclerView.ViewHolder(binding.root){

        // Defino los elementos de la vista a traves del binding
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

        init {
            itemView.setOnClickListener {
                listener.onServiceClick(adapterPosition)
            }
        }
    }

    private lateinit var mListener : onServiceClickListener

    interface onServiceClickListener{
        fun onServiceClick(position: Int)
    }

    fun setOnServiceClickListener(listener:onServiceClickListener){
        mListener = listener
    }
}

