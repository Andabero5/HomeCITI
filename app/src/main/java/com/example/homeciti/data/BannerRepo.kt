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

    // Funcion obtener la lista de objetos de tipo banner
    fun getBannerData(): LiveData<MutableList<BannerService>>? {

        var mutableDataBanner = MutableLiveData<MutableList<BannerService>>()
        var bannerApiInterface= BannerApiInterface.create().getBanners(Constants.BANNER_SERVICE_QUERY)

        // Consumo del servicio
        bannerApiInterface.enqueue( object : Callback<BannerList> {
            override fun onResponse(
                call: Call<BannerList>,
                response: Response<BannerList>
            ) {
                val listData = mutableListOf<BannerService>()
                val bannerArray = response.body()

                bannerArray?.let { banners ->

                    banners.data?.let {
                        for (document in it){
                            val imgIcon = document.icon
                            val txtLabel = document.promoIcon
                            val bgColor = document.backgroundColor

                            val banner = BannerService(imgIcon,txtLabel, bgColor)
                            listData.add(banner)
                        }
                    }

                    mutableDataBanner.value = listData

                }
            }

            override fun onFailure(call: Call<BannerList>, t: Throwable) {
                println("------------ERROR-------------")
                println("Error Banner to call Repo")
                mutableDataBanner.value = mutableListOf()
            }
        })

        return mutableDataBanner
    }
}