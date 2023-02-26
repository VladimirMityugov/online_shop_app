package com.example.onlineShopApp.data.remote.latest_goods

data class LatestGoodsDto(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Double
)