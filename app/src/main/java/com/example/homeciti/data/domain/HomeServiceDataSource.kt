package com.example.homeciti.data.domain

import com.example.homeciti.data.model.HomeServiceList
import com.example.homeciti.remote.homeService.WebServiceHome

class HomeServiceDataSource(private val webService : WebServiceHome) {
    suspend fun getHomeServiceList(): HomeServiceList = webService.getHomeServiceList()
}