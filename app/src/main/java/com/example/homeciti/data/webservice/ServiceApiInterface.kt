package com.example.homeciti.data.webservice

import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.QuickAccessList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApiInterface {

    @GET(Constants.COMPLEMENT_QUICKACCESS_SERVICE_URL)
    fun getQuickAccess(@Query("category") type:String) : Call<QuickAccessList>

    //fun getQuickAccess() : Call<QuickAccessList>

    companion object {

        private var BASE_URL = Constants.BASE_QUICKACCESS_SERVICE_URL
        fun create() : ServiceApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ServiceApiInterface::class.java)

        }
    }
}