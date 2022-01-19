package com.example.homeciti.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeciti.data.model.*
import com.example.homeciti.data.model.ServiceProvider.Companion.banners
import com.example.homeciti.data.model.ServiceProvider.Companion.generals
import com.example.homeciti.data.model.ServiceProvider.Companion.homes
import com.example.homeciti.data.model.ServiceProvider.Companion.services
import com.example.homeciti.domain.HomeRepository
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class Repo {

    // Funcion devolver la lista de objetos tipo home
    fun getHomeData():MutableLiveData<MutableList<HomeService>>{
        val mutableDataHome = MutableLiveData<MutableList<HomeService>>()

        val listData = mutableListOf<HomeService>()

        for (document in homes){
            val txtType = document.type
            val titleObj = document.titleObj
            val showMore = document.showMore
            val intOrder = document.order
            val intColumns = document.columns

            //val txtTitleObj = document.titleObj.title
            //val txtColorObj = document.titleObj.textColor

            //val titleShowMore = document.showMore.title
            //val txtColorShowMore = document.showMore.textColor
            //val visibilityShowMore = document.showMore.visibility

            //val title = TitleObj(txtTitleObj,txtColorObj)
            //val show = ShowMore(titleShowMore,txtColorShowMore,visibilityShowMore)

            val home = HomeService(txtType,titleObj, showMore, intColumns, intOrder)
            listData.add(home)

        }
        mutableDataHome.value = listData

        return mutableDataHome
    }

    // Funcion devolver la lista de objetos de tipo general
    fun getGeneralData(): MutableLiveData<MutableList<GeneralService>> {
        val mutableDataGeneral = MutableLiveData<MutableList<GeneralService>>()

        val listData = mutableListOf<GeneralService>()
        for (document in generals){
            val txtTitle = document.type
            val imgIcon = document.icon
            val txtLabel = document.promoIcon
            val bgColor = document.backgroundColor

            val general = GeneralService(txtTitle,imgIcon,txtLabel, bgColor)
            listData.add(general)
        }

        mutableDataGeneral.value = listData
        return mutableDataGeneral
    }

    // Funcion devolver la lista de objetos de tipo service de quickaccess
    fun getServiceData(context: Context): LiveData<MutableList<Service>>? {

        // crear listas que se van a completar con la info consumida
        val mutableDataService = MutableLiveData<MutableList<Service>>()
        val listData = mutableListOf<Service>()

        // consumir archivo
        val repository = HomeRepository().getTutorials(context)

        try {
            val obj = JSONObject(repository)
            val servicesArray = obj.getJSONArray("services")

            for (i in 0 until servicesArray.length()){
                val document = servicesArray.getJSONObject(i)

                val txtTitle = document.getString("type")
                val imgIcon = document.getString("icon")
                val txtLabel = document.getString("promoIcon")
                val bgColor = document.getString("backgroundColor")

                val service = Service(txtTitle,imgIcon,txtLabel, bgColor)
                println("MI SERVICE ES:")
                println(service)
                listData.add(service)
            }

        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

        mutableDataService.value = listData
        return mutableDataService

        /*

        val mutableDataService = MutableLiveData<MutableList<Service>>()
        val listData = mutableListOf<Service>()

        for (document in services){
            val txtTitle = document.type
            val imgIcon = document.icon
            val txtLabel = document.promoIcon
            val bgColor = document.backgroundColor

            val service = Service(txtTitle,imgIcon,txtLabel, bgColor)
            listData.add(service)
        }

        mutableDataService.value = listData
        return mutableDataService

         */


    }

    fun getBannerData():LiveData<MutableList<BannerService>>{
        val mutableDataBanner = MutableLiveData<MutableList<BannerService>>()

        val listData = mutableListOf<BannerService>()
        for (document in banners){
            val imgIcon = document.icon
            val txtLabel = document.promoIcon
            val bgColorLabel = document.backgroundColor

            val banner = BannerService(imgIcon, txtLabel, bgColorLabel)
            listData.add(banner)
        }

        mutableDataBanner.value = listData
        return mutableDataBanner
    }

}