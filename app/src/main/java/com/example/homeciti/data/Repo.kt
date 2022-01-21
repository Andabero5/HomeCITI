package com.example.homeciti.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeciti.data.model.*
import com.example.homeciti.data.model.ServiceProvider.Companion.banners
import com.example.homeciti.data.model.ServiceProvider.Companion.generals
import com.example.homeciti.data.model.ServiceProvider.Companion.homes
import com.example.homeciti.data.model.ServiceProvider.Companion.services
import com.example.homeciti.data.webservice.GeneralApiService
import com.example.homeciti.data.webservice.HomeApiService
import com.example.homeciti.data.webservice.RetrofitHelper
import com.example.homeciti.data.webservice.RetrofitHelper.getRetrofit
import com.example.homeciti.domain.HomeRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.create
import java.io.IOException
import java.nio.charset.Charset

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
    fun getGeneralData(context : Context): MutableLiveData<MutableList<GeneralService>> {
        val mutableDataGeneral = MutableLiveData<MutableList<GeneralService>>()
        val listData = mutableListOf<GeneralService>()

        // consumir archivo
        val repository = HomeRepository().getServices(context)

        try {

            // En caso de convertir
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
        return mutableDataGeneral
        /*
        CoroutineScope(Dispatchers.IO).launch {

            val call = getRetrofit().create(GeneralApiService::class.java).getGeneralServices()
            val iter = call.body()
            println("CALL")
            println(call)
            println("PUPY")
            println(iter)

                try {

                    for (document in iter!!.general) {
                        println("MI DOCUMENT ES")
                        println(document)
                        val txtTitle = document.type
                        val imgIcon = document.icon
                        val txtLabel = document.promoIcon
                        val bgColor = document.backgroundColor

                        val general = GeneralService(txtTitle, imgIcon, txtLabel, bgColor)
                        listData.add(general)
                    }

                    println("LISTA EN TRY")
                    println(listData)

                } catch (e: JSONException) {
                    //exception
                    e.printStackTrace()
                }

            Thread{
                println("LISTA EN GLOBAL SCOPE")
                println(listData)
                mutableDataGeneral.postValue(listData)
            }
        }

         */

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

        CoroutineScope(Dispatchers.IO).launch {
            // consumir archivo
            //val repository = HomeRepository().getTutorials(context)
            val retrofit = getRetrofit().create(HomeApiService::class.java).getAllServices()
            val servi = retrofit.body()

            if(retrofit.isSuccessful){

                // Llamada correcta
                try {
                    //val obj = JSONObject(servi)
                    //val servicesArray = servi.getJSONArray("services")
                    val servicesArray = servi
                    println("Imprimir el servicesArray")
                    println(servicesArray)

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



            }else{
                // Llamada incorrecta
                    showError(context)
                println("Error")
            }
        }
        mutableDataService.value = listData
        return mutableDataService


         */

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