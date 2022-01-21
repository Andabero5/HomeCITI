package com.example.homeciti.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeciti.data.Repo
import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.data.model.Service

class GeneralViewModel:ViewModel() {

    // instanciar el repo
    private val repo = Repo()

    // funcion para devolver mi lista de datos
    fun fetchGeneralData(context : Context): LiveData<MutableList<GeneralService>> {
        val mutableData = MutableLiveData<MutableList<GeneralService>>()
        repo.getGeneralData(context)?.observeForever{
            mutableData.value = it
        }

        return mutableData
    }
}