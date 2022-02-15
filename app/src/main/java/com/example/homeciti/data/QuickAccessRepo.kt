package com.example.homeciti.data

import androidx.lifecycle.MutableLiveData
import com.example.homeciti.data.model.QuickAccessList
import com.example.homeciti.data.model.QuickAccessService
import com.example.homeciti.data.webservice.ServiceApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuickAccessRepo {

    // Funcion devolver la lista de objetos de tipo service de quickaccess
    fun getServiceData(): MutableLiveData<MutableList<QuickAccessService>>? {

        val mutableDataService = MutableLiveData<MutableList<QuickAccessService>>()
        val listData = mutableListOf<QuickAccessService>()
        val serviceApiInterface = ServiceApiInterface.create().getQuickAccess()

        serviceApiInterface.enqueue( object : Callback<QuickAccessList> {
            override fun onResponse(
                call: Call<QuickAccessList>,
                response: Response<QuickAccessList>
            ) {
                var serviceArray = response.body()
                serviceArray?.let { services ->

                    for (document in services.data){

                        val txtTitle = document.type
                        val imgIcon = document.icon
                        val txtLabel = document.promoIcon
                        val bgColor = document.backgroundColor

                        val service = QuickAccessService(txtTitle,imgIcon,txtLabel, bgColor)
                        listData.add(service)
                    }

                    /*
                    for (document in services){

                        val txtTitle = document.type
                        val imgIcon = document.icon
                        val txtLabel = document.promoIcon
                        val bgColor = document.backgroundColor

                        val service = QuickAccessService(txtTitle,imgIcon,txtLabel, bgColor)
                        listData.add(service)
                    }

                     */

                    mutableDataService.value = listData

                }
            }

            override fun onFailure(call: Call<QuickAccessList>, t: Throwable) {
                println("Error QuickAccess to call Repo")
            }
        })

        return mutableDataService
    }
}