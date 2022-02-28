package com.example.homeciti.data.model

data class GeneralService(
    val type: String = "",
    val icon: String = "",
    val promoIcon: String? = "",
    val backgroundColor: String? = "",
)

data class GeneralList(
    val responseCode: String = "",
    val responseMessage: String = "",
    val responseSubject: String = "",
    val data: MutableList<GeneralService> = mutableListOf(),
)