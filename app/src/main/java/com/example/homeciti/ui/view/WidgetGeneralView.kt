package com.example.homeciti.ui.view

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.R
import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.ui.adapters.GeneralAdapter

class WidgetGeneralView @JvmOverloads constructor(context: Context, var item : HomeService
    ) : ConstraintLayout(context),
    MyViewModelAccessor by MyViewModelInjector(context){
    private lateinit var lblWidget : TextView
    private lateinit var btnWidget : Button
    private lateinit var rvWidget : RecyclerView

    private lateinit var adapterGeneral : GeneralAdapter

    init {
        Log.d(TAG, "Kotlin init block called WidgetGeneralView.")
        View.inflate(context, R.layout.layout_general_home_partner, this)
        subscribe()
    }

    private fun subscribe() {
        lblWidget = findViewById(R.id.lbl_general)
        btnWidget = findViewById(R.id.btn_general_seemore)
        rvWidget = findViewById(R.id.rv_general)

        // Lo colocamos invisible para mostrar el Shimmer
        rvWidget.visibility = View.INVISIBLE

        // Titulo del widget
        if(item.header == null){
            lblWidget.visibility = View.GONE
        }else{
            // Configuracion del titulo label (lbl)
            item.header?.let { titleObj ->

                if(titleObj.visibility){

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
                }else lblWidget.visibility = View.GONE
            }
        }

        // Configuracion del boton showMore del widget(btn)
        if(item.btnConfig == null){
            btnWidget.visibility = View.GONE
        }else{
            item.btnConfig?.let { showMore ->
                if (showMore.visibility){

                    // Titulo
                    btnWidget.visibility = View.VISIBLE
                    btnWidget.text = showMore.title

                    // Color
                    if(!(showMore.textColor.isNullOrEmpty())){
                        try {
                            btnWidget.setTextColor(showMore.textColor.toColorInt())
                        }catch (e: Exception){
                            btnWidget.setTextColor(Constants.COLOR_DEFAULT_TEXT.toColorInt())
                        }
                    }else btnWidget.setTextColor(Constants.COLOR_DEFAULT_TEXT.toColorInt())
                }else btnWidget.visibility = View.GONE
            }
        }

        // RecyclerView
        adapterGeneral = GeneralAdapter(context)
        rvWidget.layoutManager = GridLayoutManager(context,item.columns)
        rvWidget.adapter = adapterGeneral

        generalViewModel.fetchGeneralData().observe(activity,{
            Log.d(TAG, "Adapter General.")

            adapterGeneral.setListData(it)
            adapterGeneral.notifyDataSetChanged()
        })

        // Lo colocamos visible para mostrar el recycler
        rvWidget.visibility = View.VISIBLE
    }

    /** more code here **/

    companion object {
        private const val TAG = "WidgetGeneral_View"
    }
}