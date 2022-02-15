package com.example.homeciti.data

import androidx.lifecycle.MutableLiveData
import com.example.homeciti.data.model.*
import com.example.homeciti.data.model.ServiceProvider.Companion.homes
import com.example.homeciti.data.webservice.GeneralApiInterface
import com.example.homeciti.data.webservice.HomeApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repo {

    // Funcion devolver la lista de objetos tipo home
    fun getHomeData():MutableLiveData<MutableList<HomeService>>{
        val mutableDataHome = MutableLiveData<MutableList<HomeService>>()
        val listData = mutableListOf<HomeService>()

        /*
        for (document in homes){
            val txtType = document.type
            val txtHeader = document.header
            val btnConfig = document.btnConfig
            val intOrder = document.order
            val intColumns = document.columns

            val home = HomeService(txtType, intOrder, txtHeader, btnConfig, intColumns)
            listData.add(home)
        }
        mutableDataHome.value = listData
         */

        val apiInterface= HomeApiInterface.create().getHomes()

        apiInterface.enqueue( object : Callback<HomeList> {
            override fun onResponse(
                call: Call<HomeList>,
                response: Response<HomeList>
            ) {
                val generalArray = response.body()

                generalArray?.let { generals ->

                    for (document in generals.data){

                        val txtTitle = document.type
                        val intOrder = document.order
                        val txtHeader = document.header
                        val btnConfig = document.btnConfig
                        val intColumns = document.columns

                        val home = HomeService(txtTitle, intOrder, txtHeader, btnConfig, intColumns)
                        listData.add(home)
                    }

                    mutableDataHome.value = listData

                }
            }

            override fun onFailure(call: Call<HomeList>, t: Throwable) {
                println("Error Call Home Repo - JSON")
            }
        })


        return mutableDataHome
    }

}