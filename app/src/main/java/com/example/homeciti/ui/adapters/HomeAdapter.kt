package com.example.homeciti.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.data.model.Service
import com.example.homeciti.databinding.FragmentHomeBinding
import com.example.homeciti.databinding.LayoutGeneralHomePartnerBinding

class HomeAdapter(
    private val homeList: List<HomeService>,
    private val quickacces: List<Service>,
    private val GeneralHome: List<Service>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentHomeBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeAdapter.HomeViewHolder -> holder.bind(homeList[position])
        }
    }

    override fun getItemCount(): Int = homeList.size

    private inner class HomeViewHolder(private val binding: FragmentHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeService) {
            val layoutManager =
                GridLayoutManager(itemView.context, 3, GridLayoutManager.VERTICAL, false)
            layoutManager.initialPrefetchItemCount = 3
            val titledSectionRecycler = binding.rvHome
            titledSectionRecycler.run {
                this.setRecycledViewPool(RecyclerView.RecycledViewPool())
                this.layoutManager = layoutManager
                when (item.type) {
                    "WIDGET_GENERAL" -> {
                        val generalHomeAdapter = GeneralHomeAdapter(GeneralHome)
                        this.adapter = generalHomeAdapter
                    }
                    "WIDGET_QUICK_ACCESS" -> this.adapter = QuickAccessAdapter(quickacces)
                }
            }
        }
    }
}
