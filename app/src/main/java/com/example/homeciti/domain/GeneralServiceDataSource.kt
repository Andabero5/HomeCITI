package com.example.homeciti.domain

import com.example.homeciti.data.model.GeneralServiceList
import com.example.homeciti.remote.generalService.WebServiceGeneral

class GeneralServiceDataSource (private val webService: WebServiceGeneral) {
    suspend fun getGeneralServiceList(): GeneralServiceList =webService.getGeneralServiceList()
}