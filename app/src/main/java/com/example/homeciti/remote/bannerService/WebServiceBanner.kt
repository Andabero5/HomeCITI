package com.example.homeciti.remote.bannerService

import com.example.homeciti.data.model.BannerServiceList
import retrofit2.http.GET


interface WebServiceBanner {
    @GET("example")
    suspend fun getBannerServiceList(): BannerServiceList
}
