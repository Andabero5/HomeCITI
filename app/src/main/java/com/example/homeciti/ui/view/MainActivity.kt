package com.example.homeciti.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ethanhua.skeleton.SkeletonScreen
import com.example.homeciti.R
import com.example.homeciti.data.model.Service
import com.example.homeciti.databinding.ActivityMainBinding
import com.example.homeciti.ui.adapters.ServiceAdapter
import com.example.homeciti.ui.viewmodel.ServiceViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.faltenreich.skeletonlayout.Skeleton

class MainActivity : AppCompatActivity() {

    // Declaracion del adapter tipo ServiceAdapter
    //private lateinit var adapter : ServiceAdapter

    // Declaracion del viewmodel
    //private val serviceViewModel by lazy { ViewModelProviders.of(this).get(ServiceViewModel::class.java) }

    /*
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

     */

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView
        //var rv_quickaccess = findViewById<RecyclerView>(R.id.rv_quickaccess)
        //rv_quickaccess.layoutManager = GridLayoutManager(this, 4)

        // Asignar el adaptador
        //adapter = ServiceAdapter(this)
        //rv_quickaccess.adapter = adapter

        //observeData()

        /*
        // Agregando un nuevo objeto tipo Service a la lista mutable
        services.add(Service("spanish","https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","Spidy","blue"))

        adapter.setListData(services)
        adapter.notifyDataSetChanged()
         */

    }

    /*
    fun observeData(){

        serviceViewModel.fetchServiceData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()

        })
    }

     */

}