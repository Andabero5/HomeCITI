package com.example.homeciti.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeciti.data.model.*
import com.example.homeciti.data.model.ServiceProvider.Companion.banners
import com.example.homeciti.data.model.ServiceProvider.Companion.generals
import com.example.homeciti.data.model.ServiceProvider.Companion.homes
import com.example.homeciti.data.model.ServiceProvider.Companion.services

class Repo {

    // Funcion devolver la lista de objetos tipo home
    fun getHomeData():MutableLiveData<MutableList<HomeService>>{
        val mutableDataHome = MutableLiveData<MutableList<HomeService>>()

        val listData = mutableListOf<HomeService>()

        for (document in homes){
            val txtType = document.type
            //val titleObj = document.titleObj
            //val showMore = document.showMore
            val intOrder = document.order
            val intColumns = document.columns

            val txtTitleObj = document.titleObj.title
            val txtColorObj = document.titleObj.textColor

            val titleShowMore = document.showMore.title
            val txtColorShowMore = document.showMore.textColor
            val visibilityShowMore = document.showMore.visibility

            val title = TitleObj(txtTitleObj,txtColorObj)
            val show = ShowMore(titleShowMore,txtColorShowMore,visibilityShowMore)

            val home = HomeService(txtType,title, show, intColumns, intOrder)
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
    fun getServiceData():LiveData<MutableList<Service>>{
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