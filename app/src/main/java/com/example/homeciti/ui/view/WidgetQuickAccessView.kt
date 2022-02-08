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
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.ui.adapters.QuickAccessAdapter

class WidgetQuickAccessView @JvmOverloads constructor(context: Context, var item : HomeService
    ) : ConstraintLayout(context),
    MyViewModelAccessor by MyViewModelInjector(context){
        private lateinit var lbl_widget : TextView
        private lateinit var btn_widget : Button
        private lateinit var rv_widget : RecyclerView

        private lateinit var adapterQuickAccess : QuickAccessAdapter

        init {
            Log.d(TAG, "Kotlin init block called.")
            View.inflate(context, R.layout.layout_quickaccess_home_partner, this)
            subscribe()
        }

        private fun subscribe(){
            lbl_widget = findViewById(R.id.lbl_quickaccess)
            btn_widget = findViewById(R.id.btn_quickaccess_seemore)
            rv_widget = findViewById(R.id.rv_quickaccess)

            // Lo colocamos invisible para mostrar el Shimmer
            rv_widget.visibility = View.INVISIBLE
            // -------------------------------------

            lbl_widget.text = item.titleObj.title
            lbl_widget.setTextColor(item.titleObj.textColor.toColorInt())

            // Boton del layout
            if (item.showMore.visibility) {
                btn_widget.visibility = View.VISIBLE
                btn_widget.text = item.showMore.title
                btn_widget.setTextColor(item.showMore.textColor.toColorInt())
            }

            // El adapter
            adapterQuickAccess = QuickAccessAdapter(context)
            rv_widget.layoutManager = GridLayoutManager(context,item.columns)
            rv_widget.adapter = adapterQuickAccess

            quickAccessViewModel.fetchServiceData(context).observe(activity,{
                Log.d(TAG, "Adapter.")

                adapterQuickAccess.setListData(it)
                adapterQuickAccess.notifyDataSetChanged()
            })

            // Lo colocamos visible para mostrar el recycler
            rv_widget.visibility = View.VISIBLE
        }

        /** more code here **/

        companion object {
            private const val TAG = "Widget_View_Kotlin"
        }
    }