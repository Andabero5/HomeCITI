package com.example.homeciti.data.model

data class HomeService(
    val type: String = " ",
    val titleObj: TitleObj = TitleObj(),
    val showMore: ShowMore = ShowMore(),
    val columns:Int = 0,
    val order:Int = 0
)