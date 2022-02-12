package com.example.homeciti.data.model

data class HomeService(
    val type: String = "",
    val order: Int = 0,
    val header: HeaderObj = HeaderObj(),
    val btnConfig: BtnConfigObj = BtnConfigObj(),
    val columns: Int = 1

)

data class HeaderObj(
    val title: String = "",
    val textColor: String? = "",
    val visibility: Boolean = true
)

data class BtnConfigObj(
    val title: String = "",
    val textColor: String? = "",
    val visibility: Boolean = false
)