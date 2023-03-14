package com.chocky_development.data.remote.sale_goods

data class SaleGoodsDto(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
)