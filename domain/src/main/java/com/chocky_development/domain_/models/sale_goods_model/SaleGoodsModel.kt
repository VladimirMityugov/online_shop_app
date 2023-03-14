package com.chocky_development.domain_.models.sale_goods_model

data class SaleGoodsModel(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
)