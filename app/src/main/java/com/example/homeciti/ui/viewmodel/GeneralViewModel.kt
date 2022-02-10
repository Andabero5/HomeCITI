package com.example.homeciti.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeciti.data.GeneralRepo
import com.example.homeciti.data.model.GeneralService

class GeneralViewModel:ViewModel() {

    // instanciar el repo
    private val repo = GeneralRepo()

    // funcion para devolver mi lista de datos
    fun fetchGeneralData():LiveData<MutableList<GeneralService>> {
        val mutableData = MutableLiveData<MutableList<GeneralService>>()
        repo.getGeneralData()?.observeForever{
            mutableData.value = it
        }

        return mutableData
    }
}