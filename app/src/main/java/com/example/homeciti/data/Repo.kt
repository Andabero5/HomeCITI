package com.example.homeciti.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeciti.data.model.Service
import com.example.homeciti.data.model.ServiceProvider
import com.example.homeciti.data.model.ServiceProvider.Companion.services

class Repo {

    fun getServiceData():LiveData<MutableList<Service>>{
        val mutableData = MutableLiveData<MutableList<Service>>()

        val listData = mutableListOf<Service>()
        for (document in services){
            val txtTitle = document.type
            val imgIcon = document.icon
            val txtLabel = document.promoIcon
            val bgColor = document.backgroundColor

            val service = Service(txtTitle,imgIcon,txtLabel, bgColor)
            listData.add(service)
        }

        mutableData.value = listData

        return mutableData
    }

}