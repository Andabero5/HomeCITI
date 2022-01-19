package com.example.homeciti.remote.homeService


import com.example.homeciti.data.model.HomeServiceList

interface HomeServiceRepo {
    suspend fun getHomeServiceList():HomeServiceList
}