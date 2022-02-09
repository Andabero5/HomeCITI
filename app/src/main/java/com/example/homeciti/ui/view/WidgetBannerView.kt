package com.example.homeciti.ui.view

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.*
import com.example.homeciti.R
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.ui.adapters.BannerAdapter
import me.relex.circleindicator.CircleIndicator2

class WidgetBannerView @JvmOverloads constructor(context: Context, var item : HomeService
) : ConstraintLayout(context),
    MyViewModelAccessor by MyViewModelInjector(context){

    private lateinit var lblWidget : TextView
    private lateinit var btnWidget : Button
    private lateinit var rvWidget : RecyclerView
    private lateinit var indicator2 : CircleIndicator2
    private lateinit var constraintHeader : ConstraintLayout
    private lateinit var adapterBanner : BannerAdapter

    init {
        Log.d(TAG, "Kotlin init block called WidgetBannerView.")
        View.inflate(context, R.layout.layout_banner_home_partner, this)
        subscribe()
    }

    private fun subscribe() {
        lblWidget = findViewById(R.id.lbl_banner)
        btnWidget = findViewById(R.id.btn_banner_seemore)
        rvWidget = findViewById(R.id.rv_banner)
        indicator2 = findViewById(R.id.ci_banner)
        constraintHeader = findViewById(R.id.cl_header)

        // Configuracion del titulo label (lbl)
        item.titleObj.let { titleObj ->

            // Titulo
            lblWidget.text = titleObj.title

            // Color
            if(titleObj.textColor.isNotEmpty()) lblWidget.setTextColor(titleObj.textColor.toColorInt())
        }

        // Configuracion del boton showMore (btn)
        item.showMore.let { showMore ->
            if (showMore.visibility){

                // Titulo
                btnWidget.text = showMore.title

                // Color
                if(showMore.textColor.isNotEmpty()) btnWidget.setTextColor(showMore.textColor.toColorInt())
            } else btnWidget.visibility = View.GONE
        }

        // RecyclerView
        adapterBanner = BannerAdapter(context)
        rvWidget.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rvWidget.adapter = adapterBanner

        bannerViewModel.fetchBannerData().observe(activity,{
            Log.d(TAG, "Adapter Banner.")
            adapterBanner.setListData(it)
            adapterBanner.notifyDataSetChanged()
            indicatorBanner(adapterBanner.itemCount)
        })
    }

    private fun indicatorBanner(totalItems : Int){
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvWidget)

        // Visualizar el indicador de punto de items en el recycler
        if(totalItems>1){
            indicator2.attachToRecyclerView(rvWidget,snapHelper)
            indicator2.createIndicators(totalItems,0)
            indicator2.animatePageSelected(2)
        }
    }

    companion object {
        private const val TAG = "WidgetBanner_View"
    }
}