package com.example.homeciti.data.model

data class BannerService(
    val icon: String = "",
    val promoIcon: String? = "",
    val backgroundColor: String? = ""
)

data class BannerList (
    val responseCode: String = "",
    val responseMessage: String = "",
    val responseSubject: String = "",
    val data: MutableList<BannerService> = mutableListOf(),
)