package com.example.homeciti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val services = listOf<Service>(
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
    }

    fun initRecycler(){
        val rv_quickaccess = this.findViewById(R.id.rv_quickaccess) as RecyclerView
        rv_quickaccess.layoutManager= GridLayoutManager(this,4)
        val adapter = ServiceAdapter(services)
        rv_quickaccess.adapter = adapter
    }
}