package com.example.homeciti.remote.homeService

import com.example.homeciti.data.domain.HomeServiceDataSource
import com.example.homeciti.data.model.HomeServiceList

class HomeServiceRepoImpl(private val dataSource: HomeServiceDataSource): HomeServiceRepo {
    override suspend fun getHomeServiceList(): HomeServiceList = dataSource.getHomeServiceList()
}