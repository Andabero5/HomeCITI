package com.example.homeciti.remote.bannerService

import com.example.homeciti.data.domain.BannerServiceDataSource
import com.example.homeciti.data.model.BannerServiceList

interface BannerServiceRepo {
    suspend fun getBannerServiceList():BannerServiceList
}