package com.example.onlineShopApp.domain.models.sale_goods_model

data class SaleGoods(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
)