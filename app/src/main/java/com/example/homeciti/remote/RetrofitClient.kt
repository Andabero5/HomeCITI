package com.example.homeciti.remote

import com.example.homeciti.core.Constants
import com.example.homeciti.remote.generalService.WebServiceGeneral
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val webServiceGeneral by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_GENERAL_SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
            .create(WebServiceGeneral::class.java)

    }
}