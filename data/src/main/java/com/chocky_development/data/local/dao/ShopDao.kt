package com.chocky_development.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocky_development.data.local.entities.ViewedGoods
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToViewedGoods(vararg viewedGoods: ViewedGoods)

    @Query("SELECT * FROM viewed_goods")
    fun getAllViewedGoods(): Flow<List<ViewedGoods>>

}