package com.example.onlineShopApp.domain.models.latest_goods_model

data class LatestGoods(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Double
)