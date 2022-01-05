package com.example.homeciti.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homeciti.R
import com.example.homeciti.databinding.FragmentHomeBinding
import com.example.homeciti.ui.adapters.ServiceAdapter
import com.example.homeciti.ui.viewmodel.ServiceViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    // Declaracion del adapter tipo ServiceAdapter
    private lateinit var adapter : ServiceAdapter

    // Declaracion del viewmodel
    private val serviceViewModel by lazy { ViewModelProviders.of(this).get(ServiceViewModel::class.java) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.rvQuickaccess.layoutManager = GridLayoutManager(context,4)

        // Asignar el adaptador
        adapter = ServiceAdapter(this)
        binding.rvQuickaccess.adapter = adapter

        observeData()
    }

    fun observeData(){

        serviceViewModel.fetchServiceData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()

        })


    }
}