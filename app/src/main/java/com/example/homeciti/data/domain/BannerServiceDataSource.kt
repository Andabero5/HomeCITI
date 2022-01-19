package com.example.homeciti.data.domain

import com.example.homeciti.data.model.BannerServiceList
import com.example.homeciti.remote.bannerService.WebServiceBanner

class BannerServiceDataSource(private val webService: WebServiceBanner) {
    suspend fun getBannerServiceList():BannerServiceList=webService.getBannerServiceList()
}