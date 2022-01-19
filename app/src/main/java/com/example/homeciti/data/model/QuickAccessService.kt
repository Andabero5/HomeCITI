package com.example.homeciti.data.model
data class QuickAccessServiceList(val quickAccessService: List<QuickAccessService> = listOf())
data class QuickAccessService(
    val type: String = "",
    val icon: String = "",
    val promoIcon: String = "",
    val backgroundColor: String = ""
)
