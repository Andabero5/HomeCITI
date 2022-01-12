package com.example.homeciti.ui.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.R
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.ui.adapters.GeneralAdapter

class WidgetGeneralView @JvmOverloads constructor(
    context: Context,
    var item : HomeService
    ) : ConstraintLayout(context),
    MyViewModelAccessor by MyViewModelInjector(context){
    private lateinit var lbl : TextView
    private lateinit var button : Button
    private lateinit var rv : RecyclerView

    private lateinit var adapterGeneral : GeneralAdapter


    init {
        Log.d(TAG, "Kotlin init block called.")
        View.inflate(context, R.layout.layout_general_home_partner, this)
    }

    private fun subscribe() {

        generalViewModel.fetchServiceData().observe(activity,{
            Log.d(TAG, "Adapter.")
            adapterGeneral.setListData(it)
            adapterGeneral.notifyDataSetChanged()
        })
    }

    public override fun onFinishInflate() {
        super.onFinishInflate()
        Log.d(TAG, "onFinishInflate() called.")
        //compassNeedle = findViewById(R.id.compass_needle)
        lbl = findViewById(R.id.item_label)
        button = findViewById(R.id.btn_general_seemore)
        rv = findViewById(R.id.rv_general)

        adapterGeneral = GeneralAdapter(context)
        rv.layoutManager = GridLayoutManager(context, item.columns)
        rv.adapter = adapterGeneral

        subscribe()
    }

    /** more code here **/

    companion object {
        private const val TAG = "Widget_View_Kotlin"
    }
}