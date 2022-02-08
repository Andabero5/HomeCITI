package com.example.homeciti.data

import androidx.lifecycle.MutableLiveData
import com.example.homeciti.data.model.*
import com.example.homeciti.data.model.ServiceProvider.Companion.homes

class Repo {

    // Funcion devolver la lista de objetos tipo home
    fun getHomeData():MutableLiveData<MutableList<HomeService>>{
        val mutableDataHome = MutableLiveData<MutableList<HomeService>>()
        val listData = mutableListOf<HomeService>()

        for (document in homes){
            val txtType = document.type
            val titleObj = document.titleObj
            val showMore = document.showMore
            val intOrder = document.order
            val intColumns = document.columns

            val home = HomeService(txtType,titleObj, showMore, intColumns, intOrder)
            listData.add(home)
        }

        mutableDataHome.value = listData
        return mutableDataHome
    }

}