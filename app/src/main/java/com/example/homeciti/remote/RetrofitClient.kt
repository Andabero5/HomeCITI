package com.example.homeciti.remote

import com.example.homeciti.remote.bannerService.WebServiceBanner
import com.example.homeciti.remote.generalService.WebServiceGeneral
import com.example.homeciti.remote.homeService.WebServiceHome
import com.example.homeciti.remote.quickAccessService.WebServiceQuickAccess
import com.example.homeciti.core.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val webServiceHome by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_HOME_SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
            .create(WebServiceHome::class.java)

    }
    val webServiceGeneral by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_GENERAL_SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
            .create(WebServiceGeneral::class.java)

    }
    val webServiceBanner by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_BANNER_SERVICE_API)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
            .create(WebServiceBanner::class.java)

    }
    val webServiceQuickAccess by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_QUICK_ACCESS_SERVICE_API)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
            .create(WebServiceQuickAccess::class.java)

    }
}