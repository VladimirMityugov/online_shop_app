package com.chocky_development.data.local.mappers_local

import com.chocky_development.data.local.entities.FavoriteGoods
import com.chocky_development.domain.models.favorite_goods_model.FavoriteGoodsModel


class FavoriteGoodsMapper {

    fun toFavoriteGoodsModel(favoriteGoods: FavoriteGoods): FavoriteGoodsModel {
        return FavoriteGoodsModel(
            category = favoriteGoods.category,
            image_url = favoriteGoods.image_url,
            name = favoriteGoods.name,
            price = favoriteGoods.price
        )
    }

    fun fromFavoriteGoodsModel(favoriteGoodsModel: FavoriteGoodsModel): FavoriteGoods {
        return FavoriteGoods(
            category = favoriteGoodsModel.category,
            image_url = favoriteGoodsModel.image_url,
            name = favoriteGoodsModel.name,
            price = favoriteGoodsModel.price
        )
    }

}