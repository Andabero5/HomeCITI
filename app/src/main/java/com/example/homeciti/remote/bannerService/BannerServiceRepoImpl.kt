package com.example.homeciti.remote.bannerService

import com.example.homeciti.data.domain.BannerServiceDataSource
import com.example.homeciti.data.model.BannerServiceList

class BannerServiceRepoImpl(private val datasource: BannerServiceDataSource):BannerServiceRepo {
    override suspend fun getBannerServiceList(): BannerServiceList  = datasource.getBannerServiceList()

}