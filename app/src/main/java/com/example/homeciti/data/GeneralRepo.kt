package com.example.homeciti.data

import androidx.lifecycle.MutableLiveData
import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.data.webservice.GeneralApiInterface
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*

class GeneralRepo {

    // Funcion devolver la lista de objetos de tipo general
    fun getGeneralData(): MutableLiveData<MutableList<GeneralService>>?{

        val mutableDataGeneral = MutableLiveData<MutableList<GeneralService>>()
        val listData = mutableListOf<GeneralService>()

        val apiInterface= GeneralApiInterface.create().getGenerals()

        println("API INTERFACE")
        println(apiInterface)

        apiInterface.enqueue( object : Callback<GeneralService> {
            override fun onResponse(
                call: Call<GeneralService>,
                response: Response<GeneralService>
            ) {
                val generalArray = response.body()
                println("ESTE ES MI RESPONSE")
                println(response)

                println("ESTE ES MI BODY")
                println(generalArray)
                generalArray?.let { generals ->

                    println("Entra al .let")

                    /*
                    for (document in generals){

                        val txtTitle = document.type
                        val imgIcon = document.icon
                        val txtLabel = document.promoIcon
                        val bgColor = document.backgroundColor

                        val general = GeneralService(txtTitle,imgIcon,txtLabel, bgColor)
                        listData.add(general)
                    }

                    mutableDataGeneral.value = listData

                     */

                }
            }

            override fun onFailure(call: Call<GeneralService>, t: Throwable) {
                println("Error General Repo")
            }
        })

        return mutableDataGeneral
    }
}