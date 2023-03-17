package com.chocky_development.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.chocky_development.data.local.ShopDataBase
import com.chocky_development.data.local.entities.ViewedGoods
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class ShopDaoTest {

    private lateinit var shopDataBase: ShopDataBase
    private lateinit var shopDao: ShopDao

    @Before
    fun setup() {

        shopDataBase = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = ShopDataBase::class.java
        ).build()

        shopDao = shopDataBase.shopDao()
    }

    @After
    fun tearDown() {
        shopDataBase.close()
    }

    @Test
    fun insertViewedGoods() = runBlocking {
        val testViewedGoods = ViewedGoods(
            category = "category",
            image_url = "url",
            name = "name",
            price = 10.5
        )
        shopDao.insertToViewedGoods(testViewedGoods)
        val allViewedGoods = shopDao.getAllViewedGoods().first()

        assertThat(allViewedGoods).contains(testViewedGoods)

    }


}