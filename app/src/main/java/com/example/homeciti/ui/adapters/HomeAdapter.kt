package com.example.homeciti.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.core.Resource
import com.example.homeciti.data.model.*
import com.example.homeciti.databinding.LayoutBannerHomePartnerBinding
import com.example.homeciti.databinding.LayoutGeneralHomePartnerBinding
import com.example.homeciti.databinding.LayoutQuickaccessHomePartnerBinding
import com.example.homeciti.presentation.BannerViewModel
import com.example.homeciti.presentation.GeneralViewModel

import com.example.homeciti.ui.adapters.HomeAdapter.Const.GENERAL
import com.example.homeciti.ui.adapters.HomeAdapter.Const.QUICK_ACCESS

class HomeAdapter(
    private val homeList: List<HomeService> = listOf(),
    private val generalViewModel: LiveData<Resource<GeneralServiceList>>,
    private val quickAccessViewModel: LiveData<Resource<QuickAccessServiceList>>,
    private val bannerViewModel: LiveData<Resource<BannerServiceList>>
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
                GeneralViewHolder(view)
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
                val view = LayoutBannerHomePartnerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                BannerViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (homeList[position].type) {
            "WIDGET_GENERAL" -> {
                (holder as GeneralViewHolder).bind(homeList[position])
            }
            "WIDGET_QUICK_ACCESS" -> {
                (holder as QuickAccessViewHolder).bind(homeList[position])
            }
            "WIDGET_BANNER" -> {
                //(holder as QuickAccessViewHolder).bind(homeList[position])
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


    private inner class GeneralViewHolder(private val binding: LayoutGeneralHomePartnerBinding) :
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
            generalViewModel.observe(itemView.context as LifecycleOwner) { result ->
                when (result) {
                    is Resource.Success -> {
                        binding.shimmer.visibility = View.GONE
                        binding.shimmer.stopShimmer()
                        //bind recyclerView
                        binding.rvGeneral.layoutManager =
                            GridLayoutManager(itemView.context, item.columns)
                        binding.rvGeneral.adapter = GeneralAdapter(result.data.general)
                    }
                    is Resource.Failure -> {
                        Log.d("resultJson", (result.error.message.toString()))
                        binding.shimmer.visibility = View.GONE
                        binding.shimmer.stopShimmer()
                    }
                    is Resource.Loading -> {
                        binding.shimmer.visibility = View.VISIBLE
                        binding.shimmer.startShimmer()
                    }
                }

            }

        }
    }

    private inner class BannerViewHolder(private val binding: LayoutBannerHomePartnerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeService) {
            //bind title data
            binding.lblBanner.text = item.titleObj.title
            binding.lblBanner.setTextColor(item.titleObj.textColor.toColorInt())

            //bind show more button
            if (item.showMore.visibility) {
                binding.btnBannerSeemore.visibility = View.VISIBLE
                binding.btnBannerSeemore.text = item.showMore.title
            }

            //observe api response
            bannerViewModel.observe(itemView.context as LifecycleOwner) { result ->
                when (result) {
                    is Resource.Success -> {
                        binding.shimmer.visibility = View.GONE
                        binding.shimmer.stopShimmer()
                        //bind recyclerView
                        binding.rvBanner.layoutManager =
                            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                        binding.rvBanner.adapter = BannerAdapter(result.data.bannerList)
                    }
                    is Resource.Failure -> {
                        Log.d("resultJson", (result.error.message.toString()))
                        binding.shimmer.visibility = View.GONE
                        binding.shimmer.stopShimmer()
                    }
                    is Resource.Loading -> {
                        binding.shimmer.visibility = View.VISIBLE
                        binding.shimmer.startShimmer()
                    }
                }
            }
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

            //observe api response
            quickAccessViewModel.observe(itemView.context as LifecycleOwner) { result ->
                when (result) {
                    is Resource.Success -> {
                        binding.shimmer.visibility = View.GONE
                        binding.shimmer.stopShimmer()
                        //bind recyclerView
                        binding.rvQuickAccess.layoutManager =
                            GridLayoutManager(itemView.context, item.columns)
                        binding.rvQuickAccess.adapter = QuickAccessAdapter(result.data.quickAccess)
                    }
                    is Resource.Failure -> {
                        Log.d("resultJson", (result.error.message.toString()))
                        binding.shimmer.visibility = View.GONE
                        binding.shimmer.stopShimmer()
                    }
                    is Resource.Loading -> {
                        binding.shimmer.visibility = View.VISIBLE
                        binding.shimmer.startShimmer()
                    }
                }
            }
        }
    }

}

