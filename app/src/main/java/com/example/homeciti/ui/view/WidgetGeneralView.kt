package com.example.homeciti.ui.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.R
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

            // Titulo label (lbl)
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
                    btnWidget.visibility = View.VISIBLE

                    // Color
                    if(showMore.textColor.isNotEmpty()) btnWidget.setTextColor(showMore.textColor.toColorInt())
                } else btnWidget.visibility = View.GONE
            }

            // RecyclerView
            adapterGeneral = GeneralAdapter(context)
            rvWidget.layoutManager = GridLayoutManager(context,item.columns)
            rvWidget.adapter = adapterGeneral

            generalViewModel.fetchGeneralData(context).observe(activity,{
                Log.d(TAG, "Adapter General.")

                adapterGeneral.setListData(it)
                adapterGeneral.notifyDataSetChanged()
            })
    }

        /** more code here **/

        companion object {
            private const val TAG = "WidgetGeneral_View"
        }
}