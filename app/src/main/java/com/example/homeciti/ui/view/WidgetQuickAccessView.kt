package com.example.homeciti.ui.view

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.R
import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.ui.adapters.QuickAccessAdapter
import com.facebook.shimmer.ShimmerFrameLayout

class WidgetQuickAccessView @JvmOverloads constructor(context: Context, var item: HomeService
) : ConstraintLayout(context),
    MyViewModelAccessor by MyViewModelInjector(context) {

    // Shimmer skeleton
    private lateinit var shimmer : ShimmerFrameLayout

    // Contenido del widget
    private lateinit var constraintWidget: ConstraintLayout
    private lateinit var lblWidget: TextView
    private lateinit var btnWidget: Button
    private lateinit var rvWidget: RecyclerView
    private lateinit var adapterQuickAccess: QuickAccessAdapter

    // Upload recycler
    private lateinit var clUpdate: ConstraintLayout
    private lateinit var btnUpdate: Button
    private lateinit var lblUpdate: TextView

    init {
        Log.d(TAG, "Kotlin init block called Quick Access.")
        View.inflate(context, R.layout.layout_quickaccess_home_partner, this)
        subscribe()
    }

    private fun subscribe() {

        // Shimmer skeleton
        shimmer = findViewById(R.id.shimmer_layout_quick)

        // Contenido del widget
        constraintWidget = findViewById(R.id.cl_contentWidget)
        lblWidget = findViewById(R.id.lbl_quickaccess)
        btnWidget = findViewById(R.id.btn_quickaccess_seemore)
        rvWidget = findViewById(R.id.rv_quickaccess)

        // Upload del recycler
        clUpdate = findViewById(R.id.cl_uploadRecycler)
        btnUpdate = findViewById(R.id.btn_retryUpload)
        lblUpdate = findViewById(R.id.txt_uploadStatus)

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
                    if (!(titleObj.textColor.isNullOrEmpty())) {
                        try {
                            lblWidget.setTextColor(titleObj.textColor.toColorInt())
                        } catch (e: Exception) {
                            lblWidget.setTextColor(Constants.COLOR_DEFAULT_TEXT.toColorInt())
                        }
                    } else lblWidget.setTextColor(Constants.COLOR_DEFAULT_TEXT.toColorInt())

                } else lblWidget.visibility = View.GONE
            }
        }

        // Configuracion del boton showMore (btn)
        item.btnConfig.let { showMore ->
            if (showMore != null) {
                if (showMore.visibility) {

                    // Titulo
                    btnWidget.text = showMore.title
                    btnWidget.visibility = View.VISIBLE

                    // Color
                    if (!(showMore.textColor.isNullOrEmpty())) {
                        try {
                            btnWidget.setTextColor(showMore.textColor.toColorInt())
                        } catch (e: Exception) {
                            btnWidget.setTextColor(Constants.COLOR_DEFAULT_TEXT.toColorInt())
                        }
                    } else btnWidget.setTextColor(Constants.COLOR_DEFAULT_TEXT.toColorInt())
                } else btnWidget.visibility = View.GONE
            }
        }

        // recycler
        adapterQuickAccess = QuickAccessAdapter(context)
        rvWidget.layoutManager = GridLayoutManager(context, item.columns)
        rvWidget.adapter = adapterQuickAccess

        loadRecycler()
    }

    private fun loadRecycler() {

        quickAccessViewModel.fetchQuickAccessData()?.observe(activity){ list ->
            Log.d(TAG, "Adapter QuickAccess")

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

            } else {
                stopShimmer()
                clUpdate.visibility = View.GONE
                rvWidget.visibility = View.VISIBLE

                adapterQuickAccess.setListData(list)
                adapterQuickAccess.notifyDataSetChanged()
            }
        }
    }

    private fun stopShimmer(){
        shimmer.visibility = View.GONE
        shimmer.stopShimmer()
    }

    /** more code here **/
    companion object {
        private const val TAG = "Widget_View_Kotlin"
    }
}