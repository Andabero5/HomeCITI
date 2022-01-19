package com.example.homeciti.data.model

data class Service(
    val type: String = "",
    val icon: String = "",
    val promoIcon: String = "",
    val backgroundColor: String = ""
)

data class ServiceResponse(
    val tutorials: List<Service> = listOf()
)