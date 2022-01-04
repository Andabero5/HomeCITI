package com.example.homeciti.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeciti.data.model.Service

class ServiceViewModel:ViewModel() {

    fun fetchServiceData():LiveData<MutableList<Service>>{
        val mutableData = MutableLiveData<MutableList<Service>>()
    }
}