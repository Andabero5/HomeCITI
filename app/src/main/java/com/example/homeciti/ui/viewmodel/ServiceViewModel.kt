package com.example.homeciti.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeciti.data.Repo
import com.example.homeciti.data.model.Service
import com.example.homeciti.data.model.ServiceProvider

class ServiceViewModel:ViewModel() {

    // instanciar el repo
    private val repo = Repo()

    // funcion para devolver mi lista de datos
    fun fetchServiceData():LiveData<MutableList<Service>>{
        val mutableData = MutableLiveData<MutableList<Service>>()
        repo.getServiceData().observeForever{
            mutableData.value = it
        }

        return mutableData
    }
}