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
    private lateinit var lbl : TextView
    private lateinit var button : Button
    private lateinit var rv : RecyclerView
    private lateinit var indicator2 : CircleIndicator2

    private lateinit var adapterBanner : BannerAdapter

    init {
        Log.d(TAG, "Kotlin init block called.")
        View.inflate(context, R.layout.layout_banner_home_partner, this)
        subscribe()
    }

    private fun subscribe() {
        lbl = findViewById(R.id.lbl_banner)
        button = findViewById(R.id.btn_banner_seemore)
        rv = findViewById(R.id.rv_banner)

        indicator2 = findViewById(R.id.ci_banner)

        if(item.titleObj==null){
            lbl.visibility = View.INVISIBLE
        }
        else{
            // Titulo del layout
            lbl.text = item.titleObj.title

            // por el momento texto sin color
            //lbl.setTextColor(item.titleObj.textColor.toColorInt())
        }

        if(item.showMore==null){
            button.visibility = View.INVISIBLE
        }
        else{
            // Boton del layout
            if (item.showMore.visibility) {
                button.text = item.showMore.title
                // por el momento texto sin color
                //button.setTextColor(item.showMore.textColor.toColorInt())
            }
            else{
                button.visibility = View.INVISIBLE
            }
        }

        // Esto es nuevo
        adapterBanner = BannerAdapter(context)
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv.adapter = adapterBanner

        bannerViewModel.fetchBannerData().observe(activity,{
            Log.d(TAG, "Adapter.")

            adapterBanner.setListData(it)
            adapterBanner.notifyDataSetChanged()

            indicatorBanner(adapterBanner.itemCount)
        })
    }

    fun indicatorBanner(totalItems : Int){
        // al recycler
        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv)

        indicator2.attachToRecyclerView(rv,snapHelper)
        indicator2.createIndicators(totalItems,0)
        indicator2.animatePageSelected(2)
    }

    public override fun onFinishInflate() {
        super.onFinishInflate()
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