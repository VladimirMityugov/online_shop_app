package com.chocky_development.data.remote.mappers_remote

import com.chocky_development.data.remote.latest_goods.LatestGoodsDto
import com.chocky_development.domain.models.latest_goods_model.LatestGoodsModel


class LatestGoodsMapper {

    fun toLatestGoodsModel(latestGoodsDto: LatestGoodsDto): LatestGoodsModel {
        return LatestGoodsModel(
            category = latestGoodsDto.category,
            image_url = latestGoodsDto.image_url,
            name = latestGoodsDto.name,
            price = latestGoodsDto.price
        )
    }
}