package com.example.onlineShopApp.data.repositories


import com.example.onlineShopApp.data.local.dao.FavoriteGoodsDao
import com.example.onlineShopApp.data.local.dao.ShopDao
import com.example.onlineShopApp.domain.models.favorite_goods_model.FavoriteGoodsModel
import com.example.onlineShopApp.domain.models.viewed_goods_model.ViewedGoodsModel
import com.example.onlineShopApp.domain.repositories.RepositoryLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryLocalImpl @Inject constructor(
    private val shopDao: ShopDao,
    private val favoriteGoodsDao: FavoriteGoodsDao
): RepositoryLocal{
    override fun getAllViewedGoods(): Flow<List<ViewedGoodsModel>> {
        return shopDao.getAllViewedGoods()
    }

    override suspend fun insertIntoViewedGoods(viewedGoodsModel: ViewedGoodsModel) {
        shopDao.insertToViewedGoods(viewedGoodsModel)
    }

    override fun getAllFavoriteGoods(): Flow<List<FavoriteGoodsModel>> {
        return favoriteGoodsDao.getAllFavoriteGoods()
    }

    override suspend fun insertIntoFavoriteGoods(favoriteGoodsModel: FavoriteGoodsModel) {
        favoriteGoodsDao.insertToFavoriteGoods(favoriteGoodsModel)
    }

}


