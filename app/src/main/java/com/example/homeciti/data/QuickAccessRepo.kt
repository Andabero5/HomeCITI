package com.example.homeciti.data

import androidx.lifecycle.MutableLiveData
import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.QuickAccessList
import com.example.homeciti.data.model.QuickAccessService
import com.example.homeciti.data.webservice.ServiceApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuickAccessRepo {

    // Funcion obtener la lista de objetos de tipo quickaccess
    fun getServiceData(): MutableLiveData<MutableList<QuickAccessService>>? {

        var mutableDataService = MutableLiveData<MutableList<QuickAccessService>>()
        var quickaccessApiInterface = ServiceApiInterface.create().getQuickAccess(Constants.QUICKACCESS_SERVICE_QUERY)

        // Consumo del servicio
        quickaccessApiInterface.enqueue( object : Callback<QuickAccessList> {
            override fun onResponse(
                call: Call<QuickAccessList>,
                response: Response<QuickAccessList>
            ) {
                val listData = mutableListOf<QuickAccessService>()
                val serviceArray = response.body()

                serviceArray?.let { services ->

                    services.data?.let {
                        for (document in it){

                            val txtTitle = document.type
                            val imgIcon = document.icon
                            val txtLabel = document.promoIcon
                            val bgColor = document.backgroundColor

                            val service = QuickAccessService(txtTitle,imgIcon,txtLabel, bgColor)
                            listData.add(service)
                        }
                    }

                    mutableDataService.value = listData

                }
            }

            override fun onFailure(call: Call<QuickAccessList>, t: Throwable) {
                println("------------ERROR-------------")
                println("Error QuickAccess to call Repo")
                mutableDataService.value = mutableListOf()
            }
        })

        return mutableDataService
    }
}