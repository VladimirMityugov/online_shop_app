package com.chocky_development.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chocky_development.data.local.dao.FavoriteGoodsDao
import com.chocky_development.data.local.dao.ShopDao
import com.chocky_development.data.local.entities.FavoriteGoods
import com.chocky_development.data.local.entities.ViewedGoods

@Database(
    entities = [ViewedGoods::class, FavoriteGoods::class],
    version = 1
)
abstract class ShopDataBase : RoomDatabase() {
    abstract fun shopDao(): ShopDao
    abstract fun favoritesDao(): FavoriteGoodsDao

}