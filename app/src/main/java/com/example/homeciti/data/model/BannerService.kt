package com.example.homeciti.data.model

data class BannerServiceList(val bannerList: List<BannerService> = listOf())
data class BannerService(
    val type: String = "",
    val icon: String = "",
    val promoIcon: String = "",
    val backgroundColor: String = ""
)
