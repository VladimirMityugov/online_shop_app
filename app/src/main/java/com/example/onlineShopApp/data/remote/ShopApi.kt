package com.example.onlineShopApp.data.remote

import com.example.onlineShopApp.data.remote.goods_details.GoodsDetailsDto
import com.example.onlineShopApp.data.remote.latest_goods.Latest
import com.example.onlineShopApp.data.remote.sale_goods.Sale
import retrofit2.http.GET

interface ShopApi {

    @GET("v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getItems(): Sale

    @GET("v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getViewedItems(): Latest

    @GET("v3/f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getGoodsDetails(): GoodsDetailsDto


    companion object {
        const val BASE_URl = "https://run.mocky.io/"
        private const val KEY = ""
    }
}