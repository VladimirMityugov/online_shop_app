package com.example.onlineShopApp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onlineShopApp.data.local.dao.ShopDao
import com.example.onlineShopApp.data.local.entities.ViewedGoods


@Database(
    entities = [ViewedGoods::class],
    version = 1
)
abstract class ShopDataBase : RoomDatabase() {
    abstract fun shopDao(): ShopDao
    }