package com.example.onlineShopApp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlineShopApp.data.local.entities.FavoriteGoods
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteGoodsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToFavoriteGoods(vararg favoriteGoods: FavoriteGoods)

    @Query("SELECT * FROM favorite_goods")
    fun getAllFavoriteGoods(): Flow<List<FavoriteGoods>>

}