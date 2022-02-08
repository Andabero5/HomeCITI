package com.example.homeciti.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeciti.data.model.BannerService
import com.example.homeciti.data.model.ServiceProvider

class BannerRepo {

    fun getBannerData(): LiveData<MutableList<BannerService>> {
        val mutableDataBanner = MutableLiveData<MutableList<BannerService>>()

        val listData = mutableListOf<BannerService>()
        for (document in ServiceProvider.banners){
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