package com.example.homeciti.data.webservise

import com.example.homeciti.data.model.HomeService
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
@GET
fun getHomeStructure(@Url url:String):Response<HomeService>
}