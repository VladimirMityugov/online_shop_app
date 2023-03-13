package com.example.onlineShopApp.domain.models.viewed_goods_model


data class ViewedGoodsModel(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Double
)