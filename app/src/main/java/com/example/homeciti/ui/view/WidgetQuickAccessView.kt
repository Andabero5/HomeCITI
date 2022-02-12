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
import com.example.homeciti.ui.adapters.QuickAccessAdapter

class WidgetQuickAccessView @JvmOverloads constructor(context: Context, var item : HomeService
    ) : ConstraintLayout(context),
    MyViewModelAccessor by MyViewModelInjector(context){
        private lateinit var lblWidget : TextView
        private lateinit var btnWidget : Button
        private lateinit var rvWidget : RecyclerView

        private lateinit var adapterQuickAccess : QuickAccessAdapter

        init {
            Log.d(TAG, "Kotlin init Quick Access block called.")
            View.inflate(context, R.layout.layout_quickaccess_home_partner, this)
            subscribe()
        }

        private fun subscribe(){
            lblWidget = findViewById(R.id.lbl_quickaccess)
            btnWidget = findViewById(R.id.btn_quickaccess_seemore)
            rvWidget = findViewById(R.id.rv_quickaccess)

            // Lo colocamos invisible para mostrar el Shimmer
            rvWidget.visibility = View.INVISIBLE

            // Configuracion del titulo label (lbl)
            item.header.let { titleObj ->
                if (titleObj.visibility){

                    // Titulo
                    lblWidget.visibility = View.VISIBLE
                    lblWidget.text = titleObj.title

                    // Color
                    if (!(titleObj.textColor.isNullOrEmpty())) {
                        try {
                            lblWidget.setTextColor(titleObj.textColor.toColorInt())
                        } catch (e: Exception) {
                            lblWidget.setTextColor(Constants.COLOR_DEFAULT.toColorInt())
                        }
                    } else lblWidget.setTextColor(Constants.COLOR_DEFAULT.toColorInt())

                } else lblWidget.visibility = View.GONE
            }

            // Configuracion del boton showMore (btn)
            item.btnConfig.let { showMore ->
                if (showMore.visibility){

                    // Titulo
                    btnWidget.text = showMore.title
                    btnWidget.visibility = View.VISIBLE

                    // Color
                    if(!(showMore.textColor.isNullOrEmpty())){
                        try {
                            btnWidget.setTextColor(showMore.textColor.toColorInt())
                        }catch (e: Exception){
                            btnWidget.setTextColor(Constants.COLOR_DEFAULT.toColorInt())
                        }
                    } else btnWidget.setTextColor(Constants.COLOR_DEFAULT.toColorInt())
                } else btnWidget.visibility = View.GONE
            }

            // recycler
            adapterQuickAccess = QuickAccessAdapter(context)
            rvWidget.layoutManager = GridLayoutManager(context,item.columns)
            rvWidget.adapter = adapterQuickAccess

            quickAccessViewModel.fetchQuickAccessData().observe(activity,{
                Log.d(TAG, "Adapter QuickAccess")

                adapterQuickAccess.setListData(it)
                adapterQuickAccess.notifyDataSetChanged()
            })

            // Lo colocamos visible para mostrar el recycler
            rvWidget.visibility = View.VISIBLE
        }

        /** more code here **/

        companion object {
            private const val TAG = "Widget_View_Kotlin"
        }
    }