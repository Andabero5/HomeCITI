package com.example.homeciti.remote.quickAccessService

import com.example.homeciti.data.domain.QuickAccessServiceDataSource

class QuickAccessServiceRepoImpl(private val dataSource:QuickAccessServiceDataSource):QuickAccessServiceRepo {
    override suspend fun getQuickAccessServiceList() = dataSource.getQuickAccessServiceList()
}