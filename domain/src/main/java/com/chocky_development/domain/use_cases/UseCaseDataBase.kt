package com.chocky_development.domain.use_cases

import com.chocky_development.domain.models.favorite_goods_model.FavoriteGoodsModel
import com.chocky_development.domain.models.viewed_goods_model.ViewedGoodsModel
import com.chocky_development.domain.repositories.RepositoryLocal
import kotlinx.coroutines.flow.Flow


class UseCaseDataBase (
    private val repositoryLocal: RepositoryLocal
    ) {

    fun getAllViewedGoods(): Flow<List<ViewedGoodsModel>> {
        return repositoryLocal.getAllViewedGoods()
    }

    suspend fun insertIntoViewedGoods(viewedGoodsModel: ViewedGoodsModel){
        repositoryLocal.insertIntoViewedGoods(viewedGoodsModel)
    }

    fun getAllFavoriteGoods() : Flow<List<FavoriteGoodsModel>> {
        return repositoryLocal.getAllFavoriteGoods()
    }

    suspend fun insertIntoFavoriteGoods(favoriteGoodsModel: FavoriteGoodsModel){
       repositoryLocal.insertIntoFavoriteGoods(favoriteGoodsModel)
    }

}