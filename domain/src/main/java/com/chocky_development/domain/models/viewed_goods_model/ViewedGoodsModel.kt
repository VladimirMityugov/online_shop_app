package com.chocky_development.domain.models.viewed_goods_model


data class ViewedGoodsModel(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Double
)