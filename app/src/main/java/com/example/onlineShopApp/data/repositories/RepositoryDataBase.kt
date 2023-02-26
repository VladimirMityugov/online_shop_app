package com.example.onlineShopApp.data.repositories


import com.example.onlineShopApp.data.local.dao.ShopDao
import com.example.onlineShopApp.data.local.entities.ViewedGoods
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryDataBase @Inject constructor(
    private val shopDao: ShopDao
) {

    fun getAllViewedGoods(): Flow<List<ViewedGoods>> {
        return shopDao.getAllViewedGoods()
    }

    suspend fun insertIntoViewedGoods(viewedGoods: ViewedGoods){
        return shopDao.insertToViewedGoods(viewedGoods)
    }

}