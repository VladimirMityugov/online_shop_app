package com.example.onlineShopApp.data.remote.goods_details

data class GoodsDetailsDto(
    val colors: List<String>,
    val description: String,
    val image_urls: List<String>,
    val name: String,
    val number_of_reviews: Int,
    val price: Int,
    val rating: Double
)