package com.example.homeciti.remote.generalService

import com.example.homeciti.data.model.GeneralServiceList

interface GeneralServiceRepo {
    suspend fun getGeneralServiceList():GeneralServiceList
}