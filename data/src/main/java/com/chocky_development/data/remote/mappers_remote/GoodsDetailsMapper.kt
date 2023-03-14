package com.chocky_development.data.remote.mappers_remote

import com.chocky_development.data.remote.goods_details.GoodsDetailsDto
import com.chocky_development.domain_.models.goods_details_model.GoodsDetailsModel


class GoodsDetailsMapper {

    fun toGoodsDetailsModel(goodsDetailsDto: GoodsDetailsDto): GoodsDetailsModel {
        return GoodsDetailsModel(
            colors = goodsDetailsDto.colors,
            description = goodsDetailsDto.description,
            image_urls = goodsDetailsDto.image_urls,
            name = goodsDetailsDto.name,
            number_of_reviews = goodsDetailsDto.number_of_reviews,
            price = goodsDetailsDto.price,
            rating = goodsDetailsDto.rating
        )
    }

}