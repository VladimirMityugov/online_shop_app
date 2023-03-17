package com.chocky_development.data.remote.mappers_remote

import com.chocky_development.data.remote.sale_goods.SaleGoodsDto
import com.chocky_development.domain.models.sale_goods_model.SaleGoodsModel


class SaleGoodsMapper {

    fun toSaleGoodsModel(saleGoodsDto: SaleGoodsDto): SaleGoodsModel {
        return SaleGoodsModel(
            category = saleGoodsDto.category,
            discount = saleGoodsDto.discount,
            image_url = saleGoodsDto.image_url,
            name = saleGoodsDto.name,
            price = saleGoodsDto.price
        )
    }

}