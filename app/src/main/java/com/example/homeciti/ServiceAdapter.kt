package com.example.homeciti

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ServiceAdapter(val service:List<Service>): RecyclerView.Adapter<ServiceAdapter.ServiceHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ServiceHolder(layoutInflater.inflate(R.layout.item_home_quickaccess, parent, false))
    }

    override fun onBindViewHolder(holder: ServiceHolder, position: Int) {
        holder.render(service[position])
    }

    override fun getItemCount(): Int = service.size

    // Este es mi clase holder
    class ServiceHolder(val view: View):RecyclerView.ViewHolder(view){

        val item_text = view.findViewById(R.id.item_label) as TextView
        val item_icon = view.findViewById(R.id.item_icon) as ImageView
        val promo_icon = view.findViewById(R.id.item_labeltag) as TextView

        fun render(service: Service){
            item_text.text = service.type

            if (service.promoIcon == ""){
                promo_icon.visibility = View.INVISIBLE
            } else{
                promo_icon.visibility = View.VISIBLE
            }

            if (service.backColor == "red") {
                promo_icon.setBackgroundResource(R.drawable.background_item_labeltag)
            }
            else if(service.backColor == "blue") {
                promo_icon.setBackgroundResource(R.drawable.background_item_labeltag_blue)
            }

            promo_icon.text = service.promoIcon
            Picasso.get().load(service.icon).into(item_icon)
        }
    }

}

