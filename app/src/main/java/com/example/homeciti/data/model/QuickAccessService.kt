package com.example.homeciti.data.model
data class QuickAccessServiceList(val quickAccess: List<QuickAccessService> = listOf())
data class QuickAccessService(
    val type: String = "",
    val icon: String = "",
    val promoIcon: String = "",
    val backgroundColor: String = ""
)
