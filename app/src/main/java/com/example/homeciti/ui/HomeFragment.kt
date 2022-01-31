package com.example.homeciti.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.homeciti.R
import com.example.homeciti.core.Resource
import com.example.homeciti.data.domain.BannerServiceDataSource
import com.example.homeciti.data.domain.GeneralServiceDataSource
import com.example.homeciti.data.domain.HomeServiceDataSource
import com.example.homeciti.data.domain.QuickAccessServiceDataSource
import com.example.homeciti.data.model.*
import com.example.homeciti.databinding.FragmentHomeBinding
import com.example.homeciti.presentation.*
import com.example.homeciti.remote.RetrofitClient
import com.example.homeciti.remote.bannerService.BannerServiceRepoImpl
import com.example.homeciti.remote.generalService.GeneralServiceRepoImpl
import com.example.homeciti.remote.homeService.HomeServiceRepoImpl
import com.example.homeciti.remote.quickAccessService.QuickAccessServiceRepoImpl
import com.example.homeciti.ui.adapters.HomeAdapter


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(
            HomeServiceRepoImpl(HomeServiceDataSource(RetrofitClient.webServiceHome))
        )
    }
    private val generalViewModel by viewModels<GeneralViewModel> {
        GeneralViewModelFactory(
            GeneralServiceRepoImpl(
                GeneralServiceDataSource(RetrofitClient.webServiceGeneral)
            )
        )
    }
    private val bannerViewModel by viewModels<BannerViewModel> {
        BannerViewModelFactory(
            BannerServiceRepoImpl(
                BannerServiceDataSource(
                    RetrofitClient.webServiceBanner
                )
            )
        )
    }
    private val quickAccessViewModel by viewModels<QuickAccessViewModel> {
        QuickAccessViewModelFactory(
            QuickAccessServiceRepoImpl(
                QuickAccessServiceDataSource(
                    RetrofitClient.webServiceQuickAccess
                )
            )
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        homeViewModel.getHomeServiceList().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    //show and init skeleton layout
                    binding.shimmer.startShimmer()
                    binding.shimmer.visibility = View.VISIBLE
                }
                is Resource.Failure -> {
                    //stop and hide skeleton layout
                    binding.shimmer.stopShimmer()
                    binding.shimmer.visibility = View.GONE
                }
                is Resource.Success -> {
                    //arrange the list in order
                    val sortedHomeList = result.data.home.sortedBy { it.order }.map { it }
                    //stop and hide skeleton layout
                    binding.shimmer.stopShimmer()
                    binding.shimmer.visibility = View.GONE
                    binding.rvHome.adapter = HomeAdapter(
                        sortedHomeList,
                        generalViewModel.getGeneralServiceList(),
                        quickAccessViewModel.getQuickAccessServiceList(),
                        bannerViewModel.getBannerServiceList()
                    )

                }
            }
        }

    }

}