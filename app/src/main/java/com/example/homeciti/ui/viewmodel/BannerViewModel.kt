package com.example.homeciti.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeciti.data.BannerRepo
import com.example.homeciti.data.model.BannerService

class BannerViewModel:ViewModel() {

    // instanciar el repo
    private val repo = BannerRepo()

    // funcion para devolver mi lista de datos
    fun fetchBannerData(): LiveData<MutableList<BannerService>> {
        val mutableData = MutableLiveData<MutableList<BannerService>>()
        repo.getBannerData().observeForever{
            mutableData.value = it
        }

        return mutableData
    }
}