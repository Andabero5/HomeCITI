package com.example.homeciti.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.data.model.GeneralService
import com.example.homeciti.data.model.QuickAccessService
import com.example.homeciti.data.model.Types
import com.example.homeciti.databinding.LayoutGeneralHomePartnerBinding
import com.example.homeciti.databinding.LayoutQuickaccessHomePartnerBinding
import com.example.homeciti.ui.adapters.HomeAdapter.Const.BANNER
import com.example.homeciti.ui.adapters.HomeAdapter.Const.GENERAL
import com.example.homeciti.ui.adapters.HomeAdapter.Const.QUICK_ACCESS

class HomeAdapter(
    private val homeList: List<HomeService>,
    private val quickAccess: List<QuickAccessService>,
    private val general: List<GeneralService>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private object Const {
        const val GENERAL = 0
        const val BANNER = 1
        const val QUICK_ACCESS = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            GENERAL -> {
                val view = LayoutGeneralHomePartnerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                GeneralHomeViewHolder(view)
            }
            QUICK_ACCESS -> {
                val view = LayoutQuickaccessHomePartnerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                QuickAccessViewHolder(view)
            }
            else -> {
                val view = LayoutQuickaccessHomePartnerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                QuickAccessViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (homeList[position].type) {
            "WIDGET_GENERAL" -> {
                (holder as GeneralHomeViewHolder).bind(homeList[position])
            }
            "WIDGET_QUICK_ACCESS" -> {
                (holder as QuickAccessViewHolder).bind(homeList[position])
            }
            "WIDGET_BANNER" -> {

            }
        }
    }

    override fun getItemCount(): Int = homeList.size

    override fun getItemViewType(position: Int): Int {
        return when (homeList[position].type) {
            "WIDGET_GENERAL" -> Types.WIDGET_GENERAL.const
            "WIDGET_QUICK_ACCESS" -> Types.WIDGET_QUICK_ACCESS.const
            "WIDGET_BANNER" -> Types.WIDGET_BANNER.const
            else -> -1
        }
    }

    private inner class GeneralHomeViewHolder(private val binding: LayoutGeneralHomePartnerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeService) {
            //bind title data
            binding.lblGeneral.text = item.titleObj.title
            binding.lblGeneral.setTextColor(item.titleObj.textColor.toColorInt())

            //bind show more button
            if (item.showMore.visibility) {
                binding.btnGeneralSeeMore.visibility = View.VISIBLE
                binding.btnGeneralSeeMore.text = item.showMore.title
            }
            //bind recyclerView
            binding.rvGeneral.layoutManager = GridLayoutManager(itemView.context, item.columns)
            binding.rvGeneral.adapter = GeneralAdapter(general)
        }
    }

    private inner class QuickAccessViewHolder(private val binding: LayoutQuickaccessHomePartnerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeService) {
            //bind title data
            binding.lblQuickAccess.text = item.titleObj.title
            binding.lblQuickAccess.setTextColor(item.titleObj.textColor.toColorInt())

            //bind show more button
            if (item.showMore.visibility) {
                binding.btnQuickAccessSeeMore.visibility = View.VISIBLE
                binding.btnQuickAccessSeeMore.text = item.showMore.title
            }
            //bind recyclerView
            binding.rvQuickAccess.layoutManager = GridLayoutManager(itemView.context, item.columns)
            binding.rvQuickAccess.adapter = QuickAccessAdapter(quickAccess)
        }
    }
}

