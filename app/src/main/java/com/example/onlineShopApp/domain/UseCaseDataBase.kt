package com.example.onlineShopApp.domain

import com.example.onlineShopApp.data.local.entities.FavoriteGoods
import com.example.onlineShopApp.data.local.entities.ViewedGoods
import com.example.onlineShopApp.data.repositories.RepositoryDataBase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseDataBase @Inject constructor(
    private val repositoryDataBase: RepositoryDataBase
    ) {

    fun getAllViewedGoods(): Flow<List<ViewedGoods>> {
        return repositoryDataBase.getAllViewedGoods()
    }

    suspend fun insertIntoViewedGoods(viewedGoods: ViewedGoods){
        return repositoryDataBase.insertIntoViewedGoods(viewedGoods)
    }

    fun getAllFavoriteGoods() : Flow<List<FavoriteGoods>> {
        return repositoryDataBase.getAllFavoriteGoods()
    }

    suspend fun insertIntoFavoriteGoods(favoriteGoods: FavoriteGoods){
        return repositoryDataBase.insertIntoFavoriteGoods(favoriteGoods)
    }

}