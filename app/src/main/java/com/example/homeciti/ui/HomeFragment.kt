package com.example.homeciti.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homeciti.R
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.data.model.Service
import com.example.homeciti.data.model.TitleObj
import com.example.homeciti.databinding.FragmentHomeBinding
import com.example.homeciti.ui.adapters.HomeAdapter


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private var homeList: List<HomeService> =
        mutableListOf(HomeService("WIDGET_GENERAL", TitleObj("Que quieres hacer hoy?")),HomeService("WIDGET_QUICK_ACCESS"))
    private var generalHome: List<Service> = mutableListOf(
        Service("sendMoney", promoIcon = "nuevo"),
        Service("chargePhone"),
        Service("payServices")
    )
    private var quickAccess: List<Service> = mutableListOf(
        Service("sendMoney", promoIcon = "nuevo"),
        Service("chargePhone"),
        Service("payServices")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.rvHome.adapter = HomeAdapter(homeList, quickAccess, generalHome)

    }
}