package com.example.homeciti.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeciti.data.Repo
import com.example.homeciti.data.model.BannerService
import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.data.model.Service

class BannerViewModel:ViewModel() {

    // instanciar el repo
    private val repo = Repo()

    // funcion para devolver mi lista de datos
    fun fetchBannerData(): LiveData<MutableList<BannerService>> {
        val mutableData = MutableLiveData<MutableList<BannerService>>()
        repo.getBannerData().observeForever{
            mutableData.value = it
        }

        return mutableData
    }
}