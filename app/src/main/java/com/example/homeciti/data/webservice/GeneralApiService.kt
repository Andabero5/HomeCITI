package com.example.homeciti.data.webservice

import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.data.model.GeneralServiceList
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface GeneralApiService {
    @GET
    suspend fun getGeneralServices(@Url url : String): Response<GeneralService>

}