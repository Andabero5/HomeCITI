package com.example.homeciti.remote.generalService


import com.example.homeciti.data.model.GeneralServiceList
import retrofit2.http.GET


interface WebServiceGeneral {
    @GET("example")
    suspend fun getGeneralServiceList(): GeneralServiceList

}

