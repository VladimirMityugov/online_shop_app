package com.chocky_development.data.repositories

import com.chocky_development.data.remote.ShopApi
import com.chocky_development.data.remote.mappers_remote.GoodsDetailsMapper
import com.chocky_development.data.remote.mappers_remote.LatestGoodsMapper
import com.chocky_development.data.remote.mappers_remote.SaleGoodsMapper
import com.chocky_development.data.remote.mappers_remote.SearchResultMapper
import com.chocky_development.domain_.models.goods_details_model.GoodsDetailsModel
import com.chocky_development.domain_.models.latest_goods_model.LatestGoodsModel
import com.chocky_development.domain_.models.sale_goods_model.SaleGoodsModel
import com.chocky_development.domain_.models.search_result_model.ResultsModel
import com.chocky_development.domain_.repositories.RepositoryNetwork


class RepositoryNetworkImpl (
    private val shopApi: ShopApi
): RepositoryNetwork {
    override suspend fun getSaleItems(): List<SaleGoodsModel> {
        val mapper = SaleGoodsMapper()
        return shopApi.getSaleItems().flash_sale.map{ mapper.toSaleGoodsModel(it) }
    }

    override suspend fun getViewedItems(): List<LatestGoodsModel> {
        val mapper = LatestGoodsMapper()
        return  shopApi.getViewedItems().latest.map { mapper.toLatestGoodsModel(it) }
    }

    override suspend fun getGoodsDetails(): GoodsDetailsModel {
        val mapper = GoodsDetailsMapper()
        return mapper.toGoodsDetailsModel(shopApi.getGoodsDetails())
    }

    override suspend fun getSearchResults(): ResultsModel {
        val mapper = SearchResultMapper()
        return mapper.toResultModel(shopApi.getSearchResults())
    }

}
