package com.example.homeciti.remote.quickAccessService

import com.example.homeciti.data.model.QuickAccessServiceList

import retrofit2.http.GET


interface WebServiceQuickAccess {
    @GET("example")
    suspend fun getQuickAccessServiceList(): QuickAccessServiceList
}

