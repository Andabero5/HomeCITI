package com.example.homeciti.data.model

data class QuickAccessService(
    val type: String = "",
    val icon: String = "",
    val promoIcon: String = "",
    val backgroundColor: String = ""
)

data class QuickAccessList (
    val responseCode: String = "",
    val responseMessage: String = "",
    val responseSubject: String = "",
    val data: MutableList<QuickAccessService> = mutableListOf(),
)