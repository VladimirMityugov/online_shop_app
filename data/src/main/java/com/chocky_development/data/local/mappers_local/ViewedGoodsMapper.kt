package com.chocky_development.data.local.mappers_local

import com.chocky_development.data.local.entities.ViewedGoods
import com.chocky_development.domain.models.viewed_goods_model.ViewedGoodsModel


class ViewedGoodsMapper {

    fun toViewedGoodsModel(viewedGoods: ViewedGoods): ViewedGoodsModel {
        return ViewedGoodsModel(
            category = viewedGoods.category,
            image_url = viewedGoods.image_url,
            name = viewedGoods.name,
            price = viewedGoods.price
        )
    }

    fun fromViewedGoodsModel(viewedGoodsModel: ViewedGoodsModel):ViewedGoods{
        return ViewedGoods(
            category = viewedGoodsModel.category,
            image_url = viewedGoodsModel.image_url,
            name = viewedGoodsModel.name,
            price = viewedGoodsModel.price
        )
    }

}