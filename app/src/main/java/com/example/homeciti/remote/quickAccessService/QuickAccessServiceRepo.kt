package com.example.homeciti.remote.quickAccessService

import com.example.homeciti.data.model.QuickAccessServiceList

interface QuickAccessServiceRepo {
    suspend fun getQuickAccessServiceList():QuickAccessServiceList
}