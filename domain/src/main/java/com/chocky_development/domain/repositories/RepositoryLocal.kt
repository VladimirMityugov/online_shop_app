package com.chocky_development.domain.repositories


import com.chocky_development.domain.models.favorite_goods_model.FavoriteGoodsModel
import com.chocky_development.domain.models.viewed_goods_model.ViewedGoodsModel
import kotlinx.coroutines.flow.Flow

interface RepositoryLocal {

    fun getAllViewedGoods(): Flow<List<ViewedGoodsModel>>

    suspend fun insertIntoViewedGoods(viewedGoodsModel: ViewedGoodsModel)

    fun getAllFavoriteGoods(): Flow<List<FavoriteGoodsModel>>

    suspend fun insertIntoFavoriteGoods(favoriteGoodsModel: FavoriteGoodsModel)

}