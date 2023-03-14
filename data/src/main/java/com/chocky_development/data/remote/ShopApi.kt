package com.chocky_development.data.remote

import com.chocky_development.data.remote.goods_details.GoodsDetailsDto
import com.chocky_development.data.remote.latest_goods.Latest
import com.chocky_development.data.remote.sale_goods.Sale
import com.chocky_development.data.remote.search_result.ResultsDto
import retrofit2.http.GET

interface ShopApi {

    @GET("v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getSaleItems(): Sale

    @GET("v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getViewedItems(): Latest

    @GET("v3/f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getGoodsDetails(): GoodsDetailsDto

    @GET("v3/4c9cd822-9479-4509-803d-63197e5a9e19")
    suspend fun getSearchResults(): ResultsDto


    companion object {
        const val BASE_URl = "https://run.mocky.io/"
        private const val KEY = ""
    }
}