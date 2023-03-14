package com.chocky_development.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_goods")
data class FavoriteGoods(
    @ColumnInfo
    val category: String?,
    @ColumnInfo
    val image_url: String?,
    @PrimaryKey
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val price: Double
)

