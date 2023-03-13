package com.example.onlineShopApp.domain.use_cases

import com.example.onlineShopApp.domain.models.latest_goods_model.LatestModel
import com.example.onlineShopApp.domain.models.sale_goods_model.SaleModel
import com.example.onlineShopApp.domain.models.goods_details_model.GoodsDetailsModel
import com.example.onlineShopApp.domain.models.search_result_model.ResultsModel
import com.example.onlineShopApp.domain.repositories.RepositoryNetwork
import javax.inject.Inject

class UseCaseNetwork @Inject constructor(
    private val repositoryNetwork: RepositoryNetwork
    ) {

    suspend fun getSaleItems(): SaleModel {
        return repositoryNetwork.getSaleItems()
    }

    suspend fun getViewedItems(): LatestModel {
        return repositoryNetwork.getViewedItems()
    }

    suspend fun getGoodsDetails(): GoodsDetailsModel {
        return repositoryNetwork.getGoodsDetails()
    }

    suspend fun getSearchResults(): ResultsModel {
        return repositoryNetwork.getSearchResults()
    }

}