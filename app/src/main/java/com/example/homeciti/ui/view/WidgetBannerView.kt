package com.example.homeciti.ui.view

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.*
import com.example.homeciti.R
import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.ui.adapters.BannerAdapter
import com.facebook.shimmer.ShimmerFrameLayout
import me.relex.circleindicator.CircleIndicator2

class WidgetBannerView @JvmOverloads constructor(context: Context, var item : HomeService
) : ConstraintLayout(context),
    MyViewModelAccessor by MyViewModelInjector(context){

    // Shimmer skeleton
    private lateinit var shimmer : ShimmerFrameLayout

    // Contenido del widget
    private lateinit var constraintWidget: ConstraintLayout
    private lateinit var lblWidget : TextView
    private lateinit var btnWidget : Button
    private lateinit var rvWidget : RecyclerView
    private lateinit var indicator2 : CircleIndicator2
    private lateinit var adapterBanner : BannerAdapter

    // Upload recycler
    private lateinit var clUpdate: ConstraintLayout
    private lateinit var btnUpdate: Button
    private lateinit var lblUpdate: TextView

    init {
        Log.d(TAG, "Kotlin init block called WidgetBannerView.")
        View.inflate(context, R.layout.layout_banner_home_partner, this)
        subscribe()
    }

    private fun subscribe() {

        // Shimmer skeleton
        shimmer = findViewById(R.id.shimmer_layout_banner)

        // Contenido del widget
        constraintWidget = findViewById(R.id.cl_contentWidget)
        lblWidget = findViewById(R.id.lbl_banner)
        btnWidget = findViewById(R.id.btn_banner_seemore)
        rvWidget = findViewById(R.id.rv_banner)
        indicator2 = findViewById(R.id.ci_banner)

        // Upload recycler
        clUpdate = findViewById(R.id.cl_uploadRecyclerBanner)
        btnUpdate = findViewById(R.id.btn_retryUploadBanner)
        lblUpdate = findViewById(R.id.txt_uploadStatusBanner)

        // Lo colocamos invisible para mostrar el Shimmer
        shimmer.visibility = View.VISIBLE
        shimmer.startShimmer()
        rvWidget.visibility = View.INVISIBLE

        // Titulo del widget
        if (item.header == null) {
            lblWidget.visibility = View.GONE
        } else {
            // Configuracion del titulo label (lbl)
            item.header?.let { titleObj ->

                if (titleObj.visibility) {

                    // Titulo
                    lblWidget.visibility = View.VISIBLE
                    lblWidget.text = titleObj.title

                    // Color
                    if (!titleObj.textColor.isNullOrEmpty()) {
                        try {
                            lblWidget.setTextColor(titleObj.textColor.toColorInt())
                        } catch (e: Exception) {
                            lblWidget.setTextColor(Constants.COLOR_DEFAULT_TEXT.toColorInt())
                        }
                    } else lblWidget.setTextColor(Constants.COLOR_DEFAULT_TEXT.toColorInt())

                } else lblWidget.visibility = View.GONE
            }
        }

        // Boton del widget
        if (item.btnConfig == null) {
            btnWidget.visibility = View.GONE
        } else {
            // Configuracion del boton showMore (btn)
            item.btnConfig?.let { showMore ->
                if (showMore.visibility) {

                    // Titulo
                    btnWidget.text = showMore.title
                    btnWidget.visibility = View.VISIBLE

                    // Color
                    if (!showMore.textColor.isNullOrEmpty()) {
                        try {
                            btnWidget.setTextColor(showMore.textColor.toColorInt())
                        } catch (e: Exception) {
                            btnWidget.setTextColor(Constants.COLOR_DEFAULT_TEXT.toColorInt())
                        }
                    } else btnWidget.setTextColor(Constants.COLOR_DEFAULT_TEXT.toColorInt())
                } else btnWidget.visibility = View.GONE
            }
        }

        // RecyclerView
        adapterBanner = BannerAdapter(context)
        rvWidget.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvWidget.adapter = adapterBanner
        loadRecycler()
    }
    private fun loadRecycler() {
        bannerViewModel.fetchBannerData()?.observe(activity) {list->
            Log.d(TAG, "Adapter Banner.")
            if (list.isNullOrEmpty()) {
                clUpdate.visibility = View.VISIBLE
                rvWidget.visibility = View.GONE
                stopShimmer()
                btnUpdate.setOnClickListener {
                    shimmer.visibility=View.VISIBLE
                    shimmer.startShimmer()
                    clUpdate.visibility = View.GONE
                    loadRecycler()
                }
            }else{
                stopShimmer()
                adapterBanner.setListData(list)
                adapterBanner.notifyDataSetChanged()
                indicatorBanner(adapterBanner.itemCount)
                rvWidget.visibility = View.VISIBLE
            }
        }
    }

    private fun stopShimmer(){
        shimmer.visibility = View.GONE
        shimmer.stopShimmer()
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