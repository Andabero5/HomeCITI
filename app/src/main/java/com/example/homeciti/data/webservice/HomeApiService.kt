package com.example.homeciti.data.webservice

import com.example.homeciti.data.model.HomeService
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiService {
    @GET("/.json")
    suspend fun getAllServices():Response<List<HomeService>>
}