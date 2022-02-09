package com.example.homeciti.data

import androidx.lifecycle.MutableLiveData
import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.data.webservice.GeneralApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GeneralRepo {

    // Funcion devolver la lista de objetos de tipo general
    fun getGeneralData(): MutableLiveData<MutableList<GeneralService>>?{

        val mutableDataGeneral = MutableLiveData<MutableList<GeneralService>>()
        val listData = mutableListOf<GeneralService>()
        val apiInterface= GeneralApiInterface.create().getGenerals()

        apiInterface.enqueue( object : Callback<MutableList<GeneralService>> {
            override fun onResponse(
                call: Call<MutableList<GeneralService>>,
                response: Response<MutableList<GeneralService>>
            ) {
                val generalArray = response.body()
                generalArray?.let { generals ->

                    for (document in generals){

                        val txtTitle = document.type
                        val imgIcon = document.icon
                        val txtLabel = document.promoIcon
                        val bgColor = document.backgroundColor

                        val general = GeneralService(txtTitle,imgIcon,txtLabel, bgColor)
                        listData.add(general)
                    }

                    mutableDataGeneral.value = listData

                }
            }

            override fun onFailure(call: Call<MutableList<GeneralService>>, t: Throwable) {
                println("Error")
            }
        })

        return mutableDataGeneral
    }
}