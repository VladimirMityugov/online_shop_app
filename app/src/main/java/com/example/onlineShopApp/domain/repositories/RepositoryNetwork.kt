package com.example.onlineShopApp.domain.repositories


import com.example.onlineShopApp.domain.models.goods_details_model.GoodsDetailsModel
import com.example.onlineShopApp.domain.models.latest_goods_model.LatestModel
import com.example.onlineShopApp.domain.models.sale_goods_model.SaleModel
import com.example.onlineShopApp.domain.models.search_result_model.ResultsModel


interface RepositoryNetwork {

    suspend fun getSaleItems() : SaleModel

    suspend fun getViewedItems(): LatestModel

    suspend fun getGoodsDetails(): GoodsDetailsModel

    suspend fun getSearchResults(): ResultsModel

}