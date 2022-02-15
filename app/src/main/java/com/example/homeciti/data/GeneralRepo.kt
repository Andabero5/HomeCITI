package com.example.homeciti.data

import androidx.lifecycle.MutableLiveData
import com.example.homeciti.data.model.GeneralList
import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.data.webservice.GeneralApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GeneralRepo {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.4:3000/ClaroPay-WS/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Funcion devolver la lista de objetos de tipo general
    fun getGeneralData(): MutableLiveData<MutableList<GeneralService>>?{

        val mutableDataGeneral = MutableLiveData<MutableList<GeneralService>>()
        val listData = mutableListOf<GeneralService>()

        val apiInterface= GeneralApiInterface.create().getGenerals()

        apiInterface.enqueue( object : Callback<GeneralList> {
            override fun onResponse(
                call: Call<GeneralList>,
                response: Response<GeneralList>
            ) {
                val generalArray = response.body()

                generalArray?.let { generals ->

                    for (document in generals.data){

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

            override fun onFailure(call: Call<GeneralList>, t: Throwable) {
                println("Error Call General Repo - JSON")
            }
        })

        return mutableDataGeneral
    }
}