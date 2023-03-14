package com.chocky_development.domain_.models.goods_details_model

data class GoodsDetailsModel(
    val colors: List<String>,
    val description: String,
    val image_urls: List<String>,
    val name: String,
    val number_of_reviews: Int,
    val price: Double,
    val rating: Double
)