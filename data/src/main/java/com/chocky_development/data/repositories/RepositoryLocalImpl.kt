package com.chocky_development.data.repositories


import com.chocky_development.data.local.dao.FavoriteGoodsDao
import com.chocky_development.data.local.dao.ShopDao
import com.chocky_development.data.local.mappers_local.FavoriteGoodsMapper
import com.chocky_development.data.local.mappers_local.ViewedGoodsMapper
import com.chocky_development.domain.models.favorite_goods_model.FavoriteGoodsModel
import com.chocky_development.domain.models.viewed_goods_model.ViewedGoodsModel
import com.chocky_development.domain.repositories.RepositoryLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class RepositoryLocalImpl (
    private val shopDao: ShopDao,
    private val favoriteGoodsDao: FavoriteGoodsDao
) : RepositoryLocal {
    override fun getAllViewedGoods(): Flow<List<ViewedGoodsModel>> {
        val mapper = ViewedGoodsMapper()
        val flow = shopDao.getAllViewedGoods(). map{ viewedGoodsList ->
            viewedGoodsList.map { viewedGoods -> mapper.toViewedGoodsModel(viewedGoods) }
        }
        return flow
    }

    override suspend fun insertIntoViewedGoods(viewedGoodsModel: ViewedGoodsModel) {
        val mapper = ViewedGoodsMapper()
        shopDao.insertToViewedGoods(mapper.fromViewedGoodsModel(viewedGoodsModel))
    }

    override fun getAllFavoriteGoods(): Flow<List<FavoriteGoodsModel>> {
        val mapper = FavoriteGoodsMapper()
        val flow = favoriteGoodsDao.getAllFavoriteGoods().map{ favoriteGoodsList ->
            favoriteGoodsList.map { favoriteGoods -> mapper.toFavoriteGoodsModel(favoriteGoods) }
        }
        return flow
    }

    override suspend fun insertIntoFavoriteGoods(favoriteGoodsModel: FavoriteGoodsModel) {
        val mapper = FavoriteGoodsMapper()
        favoriteGoodsDao.insertToFavoriteGoods(mapper.fromFavoriteGoodsModel(favoriteGoodsModel))
    }

}


