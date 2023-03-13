package com.example.onlineShopApp.domain.repositories


import com.example.onlineShopApp.domain.models.favorite_goods_model.FavoriteGoodsModel
import com.example.onlineShopApp.domain.models.viewed_goods_model.ViewedGoodsModel
import kotlinx.coroutines.flow.Flow

interface RepositoryLocal {

    fun getAllViewedGoods(): Flow<List<ViewedGoodsModel>>

    suspend fun insertIntoViewedGoods(viewedGoodsModel: ViewedGoodsModel)

    fun getAllFavoriteGoods(): Flow<List<FavoriteGoodsModel>>

    suspend fun insertIntoFavoriteGoods(favoriteGoodsModel: FavoriteGoodsModel)

}