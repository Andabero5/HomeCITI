package com.example.homeciti.ui.viewmodel

import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeciti.data.Repo
import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.data.model.HomeService

class HomeViewModel:ViewModel() {

    // instanciar el repo
    private val repo = Repo()

    // funcion para devolver mi lista de datos
    fun fetchHomeData(): LiveData<MutableList<HomeService>> {
        val mutableData = MutableLiveData<MutableList<HomeService>>()
        repo.getHomeData().observeForever{
            mutableData.value = it
        }

        return mutableData
    }


}