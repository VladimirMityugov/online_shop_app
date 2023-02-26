package com.example.onlineShopApp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "viewed_goods")
data class ViewedGoods(
    @ColumnInfo
    val category: String,
    @ColumnInfo
    val image_url: String,
    @PrimaryKey
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val price: Double
)