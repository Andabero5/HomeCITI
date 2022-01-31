package com.example.homeciti.remote.generalService

import com.example.homeciti.data.model.GeneralServiceList
import com.example.homeciti.domain.GeneralServiceDataSource

class GeneralServiceRepoImpl (private val dataSource: GeneralServiceDataSource):GeneralServiceRepo {
    override suspend fun getGeneralServiceList(): GeneralServiceList = dataSource.getGeneralServiceList()
}