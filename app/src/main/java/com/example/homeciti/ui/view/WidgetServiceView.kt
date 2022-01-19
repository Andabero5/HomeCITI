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
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.R
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.ui.adapters.GeneralAdapter
import com.example.homeciti.ui.adapters.ServiceAdapter
import com.facebook.shimmer.ShimmerFrameLayout

class WidgetServiceView @JvmOverloads constructor(context: Context, var item : HomeService
    ) : ConstraintLayout(context),
    MyViewModelAccessor by MyViewModelInjector(context){
        private lateinit var lbl : TextView
        private lateinit var button : Button
        private lateinit var rv : RecyclerView
        private lateinit var ll_service : LinearLayout
        private lateinit var mFrameLayout : ShimmerFrameLayout
        private lateinit var adapterService : ServiceAdapter

        init {
            Log.d(TAG, "Kotlin init block called.")
            View.inflate(context, R.layout.layout_quickaccess_home_partner, this)
            subscribe()
        }

        private fun subscribe(){
            lbl = findViewById(R.id.lbl_quickaccess)
            button = findViewById(R.id.btn_quickaccess_seemore)
            rv = findViewById(R.id.rv_quickaccess)

            mFrameLayout = findViewById(R.id.shimmer_view_container)
            ll_service = findViewById(R.id.ll_service)

            // Lo colocamos invisible para mostrar el Shimmer
            rv.visibility = View.INVISIBLE
            mFrameLayout.visibility = View.VISIBLE;
            // -------------------------------------

            lbl.text = item.titleObj.title

            lbl.setTextColor(item.titleObj.textColor.toColorInt())

            // Boton del layout
            if (item.showMore.visibility) {
                button.visibility = View.VISIBLE
                button.text = item.showMore.title
                button.setTextColor(item.showMore.textColor.toColorInt())
            }

            // El adapter
            adapterService = ServiceAdapter(context)
            rv.layoutManager = GridLayoutManager(context,item.columns)
            rv.adapter = adapterService

            serviceViewModel.fetchServiceData(context).observe(activity,{
                Log.d(TAG, "Adapter.")
                // Lo colocamos invisible para mostrar el Shimmer
                rv.visibility = View.VISIBLE
                // -------------------------------------

                adapterService.setListData(it)
                adapterService.notifyDataSetChanged()

            })

        }

        public override fun onFinishInflate() {
            super.onFinishInflate()
            println("Dentro del onFinishInflate")
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