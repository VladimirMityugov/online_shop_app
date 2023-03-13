package com.example.onlineShopApp.domain.use_cases

import com.example.onlineShopApp.domain.models.favorite_goods_model.FavoriteGoodsModel
import com.example.onlineShopApp.domain.models.viewed_goods_model.ViewedGoodsModel
import com.example.onlineShopApp.domain.repositories.RepositoryLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseDataBase @Inject constructor(
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