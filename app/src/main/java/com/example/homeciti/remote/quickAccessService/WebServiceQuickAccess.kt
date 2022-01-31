package com.example.homeciti.remote.quickAccessService

import com.example.homeciti.core.Constants.API
import com.example.homeciti.data.model.QuickAccessServiceList

import retrofit2.http.GET


interface WebServiceQuickAccess {
    @GET(API)
    suspend fun getQuickAccessServiceList(): QuickAccessServiceList
}

