package com.example.homeciti.data.model

data class GeneralServiceList(val general:List<GeneralService> =listOf())
data class GeneralService(
    val type: String = "",
    val icon: String = "",
    val promoIcon: String = "",
    val backgroundColor: String = ""
)
