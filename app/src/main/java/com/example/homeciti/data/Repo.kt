package com.example.homeciti.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeciti.core.Constants
import com.example.homeciti.data.model.*
import com.example.homeciti.data.model.ServiceProvider.Companion.banners
import com.example.homeciti.data.model.ServiceProvider.Companion.homes
import com.example.homeciti.data.webservice.GeneralApiService
import com.example.homeciti.remote.RetrofitClient
import com.example.homeciti.remote.generalService.GeneralServiceRepoImpl
import com.example.homeciti.domain.GeneralServiceDataSource
import com.example.homeciti.domain.HomeRepository
import com.example.homeciti.remote.generalService.GeneralApiInterface
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    // Funcion devolver la lista de objetos de tipo general
    fun getGeneralData(context : Context): LiveData<MutableList<GeneralService>>?{
        val mutableDataGeneral = MutableLiveData<MutableList<GeneralService>>()
        val listData = mutableListOf<GeneralService>()

        val apiInterface = GeneralApiInterface.create().getGenerals()
        println("MI API INTERFACE -------------------------")
        println(apiInterface)

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {

                if(response?.body() != null){
                    val r = response.body()
                    println("ESTE ES EL VALOR DE R")
                    println(r)
                    r?.let { generals ->

                        try {
                            val obj = JSONObject(generals)
                            val generalArray = obj.getJSONArray("general")

                            println("GENERAL ARRAY ES ")
                            println(generalArray)
                            for (i in 0 until generalArray.length()){
                                val document = generalArray.getJSONObject(i)

                                val txtTitle = document.getString("type")
                                val imgIcon = document.getString("icon")
                                val txtLabel = document.getString("promoIcon")
                                val bgColor = document.getString("backgroundColor")

                                val general = GeneralService(txtTitle,imgIcon,txtLabel, bgColor)
                                listData.add(general)
                            }

                            mutableDataGeneral.value = listData

                        } catch (e: JSONException) {
                            //exception
                            e.printStackTrace()
                        }

                    }
                }

            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
                println("ERROR")
            }

        })


        // consumir archivo
        /*
        val repository = HomeRepository().getServices(context)

        try {

            //val outputJson: String = Gson().toJson(iter)
            val obj = JSONObject(repository)
            val generalArray = obj.getJSONArray("general")

            for (i in 0 until generalArray.length()){
                val document = generalArray.getJSONObject(i)

                val txtTitle = document.getString("type")
                val imgIcon = document.getString("icon")
                val txtLabel = document.getString("promoIcon")
                val bgColor = document.getString("backgroundColor")

                val general = GeneralService(txtTitle,imgIcon,txtLabel, bgColor)
                listData.add(general)
            }

            mutableDataGeneral.value = listData

        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

         */
        return mutableDataGeneral


        //mutableDataGeneral.value = listData
        //return mutableDataGeneral
    }

    // Funcion devolver la lista de objetos de tipo service de quickaccess
    fun getServiceData(context: Context): LiveData<MutableList<Service>>? {
        // crear listas que se van a completar con la info consumida
        val mutableDataService = MutableLiveData<MutableList<Service>>()
        val listData = mutableListOf<Service>()

        // consumir archivo
        val repository = HomeRepository().getServices(context)

        try {

            // En caso de convertir
            //val outputJson: String = Gson().toJson(iter)
            val obj = JSONObject(repository)
            val servicesArray = obj.getJSONArray("services")

            for (i in 0 until servicesArray.length()){
                val document = servicesArray.getJSONObject(i)

                val txtTitle = document.getString("type")
                val imgIcon = document.getString("icon")
                val txtLabel = document.getString("promoIcon")
                val bgColor = document.getString("backgroundColor")

                val service = Service(txtTitle,imgIcon,txtLabel, bgColor)
                listData.add(service)
            }

            mutableDataService.value = listData

        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

        return mutableDataService


        /*
        INTENTO ANTERIOR
        try {
            val obj = JSONObject(repository)
            val servicesArray = obj.getJSONArray("services")

            for (i in 0 until servicesArray.length()){
                val document = servicesArray.getJSONObject(i)

                val txtTitle = document.getString("type")
                val imgIcon = document.getString("icon")
                val txtLabel = document.getString("promoIcon")
                val bgColor = document.getString("backgroundColor")

                val service = Service(txtTitle,imgIcon,txtLabel, bgColor)
                println("MI SERVICE ES:")
                println(service)
                listData.add(service)
            }

        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

        mutableDataService.value = listData
        return mutableDataService
        ------------------------------------------------------------
         */

        /*

        val mutableDataService = MutableLiveData<MutableList<Service>>()
        val listData = mutableListOf<Service>()

        for (document in services){
            val txtTitle = document.type
            val imgIcon = document.icon
            val txtLabel = document.promoIcon
            val bgColor = document.backgroundColor

            val service = Service(txtTitle,imgIcon,txtLabel, bgColor)
            listData.add(service)
        }

        mutableDataService.value = listData
        return mutableDataService

         */


    }

    private fun showError(context: Context) {
        Toast.makeText(context,"Error al pedir datos", Toast.LENGTH_SHORT).show()
    }

    fun getBannerData():LiveData<MutableList<BannerService>>{
        val mutableDataBanner = MutableLiveData<MutableList<BannerService>>()

        val listData = mutableListOf<BannerService>()
        for (document in banners){
            val imgIcon = document.icon
            val txtLabel = document.promoIcon
            val bgColorLabel = document.backgroundColor

            val banner = BannerService(imgIcon, txtLabel, bgColorLabel)
            listData.add(banner)
        }

        mutableDataBanner.value = listData
        return mutableDataBanner
    }

}