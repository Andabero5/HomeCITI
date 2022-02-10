package com.example.homeciti.data.webservice

import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.QuickAccessService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ServiceApiInterface {

    @GET(Constants.COMPLEMENT_QUICKACCESS_SERVICE_URL)
    fun getQuickAccess() : Call<MutableList<QuickAccessService>>

    companion object {

        var BASE_URL = Constants.BASE_QUICKACCESS_SERVICE_URL
        fun create() : ServiceApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ServiceApiInterface::class.java)

        }
    }
}