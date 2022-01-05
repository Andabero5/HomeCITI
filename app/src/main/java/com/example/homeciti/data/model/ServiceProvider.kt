package com.example.homeciti.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ServiceProvider {
    companion object {

        // Crear nuestra lista mutable
        val services = mutableListOf<Service>(
            Service("send Money","https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg","New","red"),
            Service("charge Phone","https://cursokotlin.com/wp-content/uploads/2017/07/flash.png","",""),
            Service("pay Services","https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg","New","red"),
            Service("sendMoney","https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg","",""),
            Service("sendMoney","https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","Favorite","blue"),
            Service("send Money","https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg","New","red"),
            Service("charge Phone","https://cursokotlin.com/wp-content/uploads/2017/07/flash.png","",""),
            Service("pay Services","https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg","New","red"),
            Service("sendMoney","https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg","",""),
            Service("sendMoney","https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","Favorite","blue"),
            Service("send Money","https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg","New","red"),
            Service("charge Phone","https://cursokotlin.com/wp-content/uploads/2017/07/flash.png","",""),
            Service("pay Services","https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg","New","red"),
            Service("sendMoney","https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg","",""),
            Service("sendMoney","https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","Favorite","blue"),
            Service("send Money","https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg","New","red"),
            Service("charge Phone","https://cursokotlin.com/wp-content/uploads/2017/07/flash.png","",""),
            Service("pay Services","https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg","New","red"),
            Service("sendMoney","https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg","",""),
            Service("sendMoney","https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","Favorite","blue")
        )
    }
}