package com.example.homeciti.data.webservice

import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.GeneralList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GeneralApiInterface {

    @GET(Constants.COMPLEMENT_GENERAL_SERVICE_URL)
    fun getGenerals(@Query("id") type:String) : Call<GeneralList>

    //fun getGenerals() : Call<GeneralList>

    companion object {

        private var BASE_URL = Constants.BASE_GENERAL_SERVICE_URL
        fun create() : GeneralApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(GeneralApiInterface::class.java)

        }
    }
}