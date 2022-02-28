package com.example.homeciti.data.webservice

import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.BannerService
import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.data.model.HomeList
import com.example.homeciti.data.model.HomeService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HomeApiInterface {

    @GET(Constants.COMPLEMENT_HOME_SERVICE_URL)
    fun getHomes() : Call<HomeList>

    companion object {

        var BASE_URL = Constants.BASE_HOME_SERVICE_URL
        fun create() : HomeApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(HomeApiInterface::class.java)

        }
    }

}