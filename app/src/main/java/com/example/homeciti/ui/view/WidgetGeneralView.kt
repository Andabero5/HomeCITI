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
import com.example.homeciti.ui.adapters.GeneralAdapter
import com.facebook.shimmer.ShimmerFrameLayout

class WidgetGeneralView @JvmOverloads constructor(context: Context, var item: HomeService
) : ConstraintLayout(context),
    MyViewModelAccessor by MyViewModelInjector(context) {

    // Shimmer skeleton
    private lateinit var shimmer : ShimmerFrameLayout

    // Contenido del widget
    private lateinit var constraintWidget: ConstraintLayout
    private lateinit var lblWidget: TextView
    private lateinit var btnWidget: Button
    private lateinit var rvWidget: RecyclerView
    private lateinit var adapterGeneral: GeneralAdapter

    // Upload recycler
    private lateinit var clUpdate: ConstraintLayout
    private lateinit var btnUpdate: Button
    private lateinit var lblUpdate: TextView

    init {
        Log.d(TAG, "Kotlin init block called WidgetGeneralView.")
        View.inflate(context, R.layout.layout_general_home_partner, this)
        subscribe()
    }

    private fun subscribe() {

        // Shimmer skeleton
        shimmer = findViewById(R.id.shimmer_layout_general)

        // Contenido del widget
        constraintWidget = findViewById(R.id.cl_contentWidget)
        lblWidget = findViewById(R.id.lbl_general)
        btnWidget = findViewById(R.id.btn_general_seemore)
        rvWidget = findViewById(R.id.rv_general)

        // Upload del recycler
        clUpdate = findViewById(R.id.cl_uploadRecyclerGeneral)
        btnUpdate = findViewById(R.id.btn_retryUploadGeneral)
        lblUpdate = findViewById(R.id.txt_uploadStatusGeneral)

        // Lo colocamos invisible para mostrar el Shimmer
        shimmer.visibility = View.VISIBLE
        shimmer.startShimmer()
        rvWidget.visibility = View.INVISIBLE

        // Hay contenido -> oculte el shimmer
        item.let { shimmer.visibility = View.GONE }

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

        // Configuracion del boton showMore del widget(btn)
        if (item.btnConfig == null) {
            btnWidget.visibility = View.GONE
        } else {
            item.btnConfig?.let { showMore ->
                if (showMore.visibility) {

                    // Titulo
                    btnWidget.visibility = View.VISIBLE
                    btnWidget.text = showMore.title

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
        // RecyclerView
        adapterGeneral = GeneralAdapter(context)
        rvWidget.layoutManager = GridLayoutManager(context, item.columns)
        rvWidget.adapter = adapterGeneral
        loadRecycler()
    }

    private fun loadRecycler() {
        generalViewModel.fetchGeneralData()?.observe(activity) { list ->
            Log.d(TAG, "Adapter General.")
            if (list.isNullOrEmpty()) {
                clUpdate.visibility = View.VISIBLE
                rvWidget.visibility = View.GONE
                btnUpdate.setOnClickListener {
                    Toast.makeText(context, "Clicked method Widget General", Toast.LENGTH_SHORT).show()
                    loadRecycler()
                }
            } else {
                adapterGeneral.setListData(list)
                adapterGeneral.notifyDataSetChanged()
                rvWidget.visibility = View.VISIBLE
            }
        }

    }

    /** more code here **/

    companion object {
        private const val TAG = "WidgetGeneral_View"
    }
}