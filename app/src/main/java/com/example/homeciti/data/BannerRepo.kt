package com.example.homeciti.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.*
import com.example.homeciti.data.webservice.BannerApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BannerRepo {

    fun getBannerData(): LiveData<MutableList<BannerService>> {
        val mutableDataBanner = MutableLiveData<MutableList<BannerService>>()
        val listData = mutableListOf<BannerService>()

        /*
        for (document in ServiceProvider.banners){
            val imgIcon = document.icon
            val txtLabel = document.promoIcon
            val bgColorLabel = document.backgroundColor

            val banner = BannerService(imgIcon, txtLabel, bgColorLabel)
            listData.add(banner)
        }

         */

        val apiInterface= BannerApiInterface.create().getBanners(Constants.BANNER_SERVICE_QUERY)

        apiInterface.enqueue( object : Callback<BannerList> {
            override fun onResponse(
                call: Call<BannerList>,
                response: Response<BannerList>
            ) {
                val bannerArray = response.body()

                bannerArray?.let { banners ->

                    for (document in banners.data){
                        val imgIcon = document.icon
                        val txtLabel = document.promoIcon
                        val bgColor = document.backgroundColor

                        val banner = BannerService(imgIcon,txtLabel, bgColor)
                        listData.add(banner)
                    }

                    mutableDataBanner.value = listData

                }
            }

            override fun onFailure(call: Call<BannerList>, t: Throwable) {
                mutableDataBanner.value = mutableListOf()
            }
        })

        return mutableDataBanner
    }
}