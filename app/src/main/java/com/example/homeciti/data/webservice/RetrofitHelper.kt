package com.example.homeciti.data.webservice

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHelper {
    fun getRetrofit(): Retrofit {

        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl("https://00f74ba44bac9180bf035a5dca3a41bd15dd34f03b-apidata.googleusercontent.com/download/storage/v1/b/example_apiget1/o/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}