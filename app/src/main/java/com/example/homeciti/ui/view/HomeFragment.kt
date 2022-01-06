package com.example.homeciti.ui.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homeciti.R
import com.example.homeciti.databinding.FragmentHomeBinding
import com.example.homeciti.ui.adapters.GeneralAdapter
import com.example.homeciti.ui.adapters.ServiceAdapter
import com.example.homeciti.ui.viewmodel.GeneralViewModel
import com.example.homeciti.ui.viewmodel.ServiceViewModel
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(R.layout.fragment_home) {

    // Declarar mi binding asociado al fragment
    private lateinit var binding: FragmentHomeBinding

    // Declaracion del adapter con su tipo
    private lateinit var adapterService : ServiceAdapter
    private lateinit var adapterGeneral : GeneralAdapter

    // PRUEBA - Declaracion del adapter del home
    private lateinit var listView:ListView
    private lateinit var arrayAdapter : ArrayAdapter<Objects>

    // PRUEBA - declaracion del adapter del home
    // private lateinit var adapterHome : HomeAdapter

    // Declaracion del viewmodel
    private val serviceViewModel by lazy { ViewModelProviders.of(this).get(ServiceViewModel::class.java) }
    private val generalViewModel by lazy { ViewModelProviders.of(this).get(GeneralViewModel::class.java)}

    // Metodo cuando la vista este creadad
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        // Asignar el comportamiento de grid al recyclerview
        binding.lyQuickaccess.rvQuickaccess.layoutManager = GridLayoutManager(context,4)
        binding.lyGeneral.rvGeneral.layoutManager = GridLayoutManager(context,3)

        //arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,)
        //adapterHome = HomeAdapter(this)
        //binding.lvHome.adapter = adapterHome

        // Asignar el adaptador
        adapterService = ServiceAdapter(this)
        binding.lyQuickaccess.rvQuickaccess.adapter = adapterService

        adapterGeneral = GeneralAdapter(this)
        binding.lyGeneral.rvGeneral.adapter = adapterGeneral

        observeData()
    }

    fun observeData(){

        serviceViewModel.fetchServiceData().observe(this, Observer {
            adapterService.setListData(it)
            adapterService.notifyDataSetChanged()

        })

        generalViewModel.fetchServiceData().observe(this,{
            adapterGeneral.setListData(it)
            adapterGeneral.notifyDataSetChanged()
        })

    }
}