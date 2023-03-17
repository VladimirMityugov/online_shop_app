package com.chocky_development.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.chocky_development.data.local.ShopDataBase
import com.chocky_development.data.local.entities.FavoriteGoods
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class FavoriteGoodsDaoTest {


    private lateinit var shopDataBase: ShopDataBase
    private lateinit var favoriteGoodsDao: FavoriteGoodsDao

    @Before
    fun setup() {
        shopDataBase = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = ShopDataBase::class.java
        ).build()

        favoriteGoodsDao = shopDataBase.favoritesDao()
    }

    @After
    fun tearDown(){
        shopDataBase.close()
    }


    @Test
    fun insertFavoriteGoods() = runBlocking{
        val favoriteGoods = FavoriteGoods(
            category = null,
            image_url = null,
            name = "name",
            price = 10.5
        )
        favoriteGoodsDao.insertToFavoriteGoods(favoriteGoods)

        val allFavoriteGoods = favoriteGoodsDao.getAllFavoriteGoods().first()
        assertThat(allFavoriteGoods).contains(favoriteGoods)
    }


}