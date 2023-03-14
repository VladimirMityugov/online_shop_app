package com.chocky_development.domain_.repositories


import com.chocky_development.domain_.models.goods_details_model.GoodsDetailsModel
import com.chocky_development.domain_.models.latest_goods_model.LatestGoodsModel
import com.chocky_development.domain_.models.sale_goods_model.SaleGoodsModel
import com.chocky_development.domain_.models.search_result_model.ResultsModel


interface RepositoryNetwork {
    suspend fun getSaleItems() : List<SaleGoodsModel>
    suspend fun getViewedItems(): List<LatestGoodsModel>
    suspend fun getGoodsDetails(): GoodsDetailsModel
    suspend fun getSearchResults(): ResultsModel

}