package com.example.homeciti.data.webservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://storage.cloud.google.com/example_apiget1/home.json")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}