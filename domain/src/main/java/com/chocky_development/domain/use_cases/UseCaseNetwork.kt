package com.chocky_development.domain.use_cases

import com.chocky_development.domain.models.goods_details_model.GoodsDetailsModel
import com.chocky_development.domain.models.latest_goods_model.LatestGoodsModel
import com.chocky_development.domain.models.sale_goods_model.SaleGoodsModel
import com.chocky_development.domain.models.search_result_model.ResultsModel
import com.chocky_development.domain.repositories.RepositoryNetwork


class UseCaseNetwork (
    private val repositoryNetwork: RepositoryNetwork
    ) {

    suspend fun getSaleItems(): List<SaleGoodsModel> {
        return repositoryNetwork.getSaleItems()
    }

    suspend fun getViewedItems(): List<LatestGoodsModel> {
        return repositoryNetwork.getViewedItems()
    }

    suspend fun getGoodsDetails(): GoodsDetailsModel {
        return repositoryNetwork.getGoodsDetails()
    }

    suspend fun getSearchResults(): ResultsModel {
        return repositoryNetwork.getSearchResults()
    }

}