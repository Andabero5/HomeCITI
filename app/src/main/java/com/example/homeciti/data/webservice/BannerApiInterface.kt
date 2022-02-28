package com.example.homeciti.data.webservice

import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.BannerList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BannerApiInterface {

    @GET(Constants.COMPLEMENT_BANNER_SERVICE_URL)
    fun getBanners(@Query("id") type:String) : Call<BannerList>
    //fun getBanners() : Call<BannerList>

    companion object {

        private var BASE_URL = Constants.BASE_BANNER_SERVICE_URL
        fun create() : BannerApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(BannerApiInterface::class.java)

        }
    }
}