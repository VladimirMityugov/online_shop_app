package com.example.onlineShopApp.domain.models.favorite_goods_model

data class FavoriteGoodsModel(
    val category: String?,
    val image_url: String?,
    val name: String,
    val price: Double
)