package com.example.homeciti.remote.generalService


import com.example.homeciti.core.Constants.API
import com.example.homeciti.data.model.GeneralServiceList
import retrofit2.http.GET


interface WebServiceGeneral {
    @GET(API)
    suspend fun getGeneralServiceList(): GeneralServiceList

}

