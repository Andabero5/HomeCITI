package com.example.homeciti.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeciti.data.QuickAccessRepo
import com.example.homeciti.data.model.QuickAccessService

class QuickAccessViewModel:ViewModel() {

    // instanciar el repo
    private val repo = QuickAccessRepo()

    // funcion para devolver mi lista de datos
    fun fetchQuickAccessData():LiveData<MutableList<QuickAccessService>>{
        val mutableData = MutableLiveData<MutableList<QuickAccessService>>()
        repo.getServiceData()?.observeForever{
            mutableData.value = it
        }

        return mutableData
    }
}