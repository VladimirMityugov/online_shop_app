package com.example.onlineShopApp.data.repositories


import com.example.onlineShopApp.data.remote.ShopApi
import com.example.onlineShopApp.data.remote.goods_details.GoodsDetailsDto
import com.example.onlineShopApp.data.remote.latest_goods.Latest
import com.example.onlineShopApp.data.remote.sale_goods.Sale
import javax.inject.Inject


class RepositoryNetwork @Inject constructor(
    private val shopApi: ShopApi
) {

    suspend fun getItems(): Sale {
        return shopApi.getItems()
    }

    suspend fun getViewedItems(): Latest{
        return shopApi.getViewedItems()
    }

    suspend fun getGoodsDetails(): GoodsDetailsDto{
        return shopApi.getGoodsDetails()
    }


}