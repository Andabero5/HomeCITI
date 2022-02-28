package com.example.homeciti.data

import androidx.lifecycle.MutableLiveData
import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.GeneralList
import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.data.webservice.GeneralApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GeneralRepo {

    // Funcion obtener la lista de objetos de tipo general
    fun getGeneralData(): MutableLiveData<MutableList<GeneralService>>? {

        val mutableDataGeneral = MutableLiveData<MutableList<GeneralService>>()
        val generalApiInterface= GeneralApiInterface.create().getGenerals(Constants.GENERAL_SERVICE_QUERY)

        // Consumo del servicio
        generalApiInterface.enqueue( object : Callback<GeneralList> {
            override fun onResponse(
                call: Call<GeneralList>,
                response: Response<GeneralList>
            ) {
                val listData = mutableListOf<GeneralService>()
                val generalArray = response.body()

                generalArray?.let { generals ->

                    generals.data?.let {
                        for (document in it){

                            val txtTitle = document.type
                            val imgIcon = document.icon
                            val txtLabel = document.promoIcon
                            val bgColor = document.backgroundColor

                            val general = GeneralService(txtTitle,imgIcon,txtLabel, bgColor)
                            listData.add(general)
                        }
                    }

                    mutableDataGeneral.value = listData

                }
            }

            override fun onFailure(call: Call<GeneralList>, t: Throwable) {
                println("------------ERROR-------------")
                println("Error General to call Repo")
                mutableDataGeneral.value = mutableListOf()

            }
        })

        return mutableDataGeneral
    }
}