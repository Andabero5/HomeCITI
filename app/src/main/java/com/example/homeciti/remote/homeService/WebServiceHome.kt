package com.example.homeciti.remote.homeService

import com.example.homeciti.core.Constants.API
import com.example.homeciti.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServiceHome {
    @GET(API)
    suspend fun getHomeServiceList(): HomeServiceList
}

