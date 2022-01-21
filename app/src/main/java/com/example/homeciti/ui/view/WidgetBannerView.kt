package com.example.homeciti.ui.view

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.R
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.ui.adapters.BannerAdapter

class WidgetBannerView @JvmOverloads constructor(context: Context, var item : HomeService
) : ConstraintLayout(context),
    MyViewModelAccessor by MyViewModelInjector(context){
    private lateinit var lbl : TextView
    private lateinit var button : Button
    private lateinit var rv : RecyclerView

    private lateinit var adapterBanner : BannerAdapter


    init {
        Log.d(TAG, "Kotlin init block called.")
        View.inflate(context, R.layout.layout_banner_home_partner, this)
        subscribe()
    }

    private fun subscribe() {
        lbl = findViewById(R.id.lbl_banner)
        button = findViewById(R.id.btn_banner_seemore)
        rv = findViewById(R.id.rv_banner)

        if(item.titleObj==null){
            lbl.visibility = View.INVISIBLE
        }
        else{
            // Titulo del layout
            lbl.text = item.titleObj.title

            // por el momento texto sin color
            //lbl.setTextColor(item.titleObj.textColor.toColorInt())
        }

        if(item.showMore==null){
            button.visibility = View.INVISIBLE
        }
        else{
            // Boton del layout
            if (item.showMore.visibility) {
                button.text = item.showMore.title
                // por el momento texto sin color
                //button.setTextColor(item.showMore.textColor.toColorInt())
            }
            else{
                button.visibility = View.INVISIBLE
            }
        }

        // Esto es nuevo
        adapterBanner = BannerAdapter(context)
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv.adapter = adapterBanner
        bannerViewModel.fetchBannerData().observe(activity,{
            Log.d(TAG, "Adapter.")

            adapterBanner.setListData(it)
            adapterBanner.notifyDataSetChanged()
        })
    }

    public override fun onFinishInflate() {
        super.onFinishInflate()
        /*
        Log.d(TAG, "onFinishInflate() called.")
        //compassNeedle = findViewById(R.id.compass_needle)
        lbl = findViewById(R.id.item_label)
        button = findViewById(R.id.btn_general_seemore)
        rv = findViewById(R.id.rv_general)

        adapterGeneral = GeneralAdapter(context)
        rv.layoutManager = GridLayoutManager(context,item.columns)
        rv.adapter = adapterGeneral

        subscribe()

         */
    }

    /** more code here **/

    companion object {
        private const val TAG = "Widget_View_Kotlin"
    }
}