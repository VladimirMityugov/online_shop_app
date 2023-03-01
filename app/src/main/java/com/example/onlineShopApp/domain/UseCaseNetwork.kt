package com.example.onlineShopApp.domain


import com.example.onlineShopApp.data.remote.goods_details.GoodsDetailsDto
import com.example.onlineShopApp.data.remote.latest_goods.Latest
import com.example.onlineShopApp.data.remote.sale_goods.Sale
import com.example.onlineShopApp.data.remote.search_result.Results
import com.example.onlineShopApp.data.repositories.RepositoryNetwork
import javax.inject.Inject


class UseCaseNetwork @Inject constructor(
    private val repositoryNetwork: RepositoryNetwork
    ) {

    suspend fun getItems(): Sale {
        return repositoryNetwork.getItems()
    }

    suspend fun getViewedItems(): Latest {
        return repositoryNetwork.getViewedItems()
    }

    suspend fun getGoodsDetails(): GoodsDetailsDto {
        return repositoryNetwork.getGoodsDetails()
    }

    suspend fun getSearchResults(): Results {
        return repositoryNetwork.getSearchResults()
    }



}