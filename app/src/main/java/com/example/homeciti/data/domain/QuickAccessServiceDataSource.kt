package com.example.homeciti.data.domain

import com.example.homeciti.data.model.QuickAccessServiceList
import com.example.homeciti.remote.quickAccessService.WebServiceQuickAccess

class QuickAccessServiceDataSource(private val webService: WebServiceQuickAccess) {
    suspend fun getQuickAccessServiceList():QuickAccessServiceList= webService.getQuickAccessServiceList()
}