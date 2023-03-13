package com.example.onlineShopApp.data.repositories


import com.example.onlineShopApp.data.remote.ShopApi
import com.example.onlineShopApp.domain.models.latest_goods_model.LatestModel
import com.example.onlineShopApp.domain.models.sale_goods_model.SaleModel
import com.example.onlineShopApp.domain.models.goods_details_model.GoodsDetailsModel
import com.example.onlineShopApp.domain.models.search_result_model.ResultsModel
import com.example.onlineShopApp.domain.repositories.RepositoryNetwork
import javax.inject.Inject


class RepositoryNetworkImpl @Inject constructor(
    private val shopApi: ShopApi
): RepositoryNetwork {
    override suspend fun getSaleItems(): SaleModel {
        return shopApi.getSaleItems()
    }

    override suspend fun getViewedItems(): LatestModel {
        return shopApi.getViewedItems()
    }

    override suspend fun getGoodsDetails(): GoodsDetailsModel {
        return shopApi.getGoodsDetails()
    }

    override suspend fun getSearchResults(): ResultsModel {
        return shopApi.getSearchResults()
    }

}
