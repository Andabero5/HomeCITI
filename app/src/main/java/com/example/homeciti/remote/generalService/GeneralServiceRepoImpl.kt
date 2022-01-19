package com.example.homeciti.remote.generalService

import com.example.homeciti.data.domain.GeneralServiceDataSource
import com.example.homeciti.data.model.GeneralServiceList

class GeneralServiceRepoImpl(private val dataSource: GeneralServiceDataSource):GeneralServiceRepo {
    override suspend fun getGeneralServiceList(): GeneralServiceList = dataSource.getGeneralServiceList()
}