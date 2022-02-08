package com.example.homeciti.ui.view

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.*
import com.example.homeciti.R
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.ui.adapters.BannerAdapter
import me.relex.circleindicator.CircleIndicator2

class WidgetBannerView @JvmOverloads constructor(context: Context, var item : HomeService
) : ConstraintLayout(context),
    MyViewModelAccessor by MyViewModelInjector(context){

    private lateinit var lbl_widget : TextView
    private lateinit var btn_widget : Button
    private lateinit var rv_widget : RecyclerView
    private lateinit var indicator2 : CircleIndicator2
    private lateinit var constraint_header : ConstraintLayout
    private lateinit var adapterBanner : BannerAdapter

    init {
        Log.d(TAG, "Kotlin init block called.")
        View.inflate(context, R.layout.layout_banner_home_partner, this)
        subscribe()
    }

    private fun subscribe() {
        lbl_widget = findViewById(R.id.lbl_banner)
        btn_widget = findViewById(R.id.btn_banner_seemore)
        rv_widget = findViewById(R.id.rv_banner)
        indicator2 = findViewById(R.id.ci_banner)
        constraint_header = findViewById(R.id.cl_header)

        // Configuracion del titulo label (lbl)
        if(item.titleObj==null){
            lbl_widget.visibility = View.GONE
        } else{
            lbl_widget.text = item.titleObj.title
        }

        // Configuracion del boton showmore (btn)
        if(item.showMore==null){
            btn_widget.visibility = View.GONE
        }
        else if (item.showMore.visibility) {
            btn_widget.text = item.showMore.title
        }
        else{
            btn_widget.visibility = View.GONE
        }


        adapterBanner = BannerAdapter(context)
        rv_widget.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_widget.adapter = adapterBanner

        bannerViewModel.fetchBannerData().observe(activity,{
            Log.d(TAG, "Adapter.")
            adapterBanner.setListData(it)
            adapterBanner.notifyDataSetChanged()
            indicatorBanner(adapterBanner.itemCount)
        })
    }

    fun indicatorBanner(totalItems : Int){
        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_widget)

        // Visualizar el indicador de punto de items en el recycler
        if(totalItems>1){
            indicator2.attachToRecyclerView(rv_widget,snapHelper)
            indicator2.createIndicators(totalItems,0)
            indicator2.animatePageSelected(2)
        }
    }

    companion object {
        private const val TAG = "Widget_View_Kotlin"
    }
}