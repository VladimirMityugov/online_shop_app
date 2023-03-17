package com.chocky_development.domain.use_cases

import app.cash.turbine.test
import com.chocky_development.domain.models.favorite_goods_model.FavoriteGoodsModel
import com.chocky_development.domain.models.viewed_goods_model.ViewedGoodsModel
import com.chocky_development.domain.repositories.RepositoryLocal
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock


@OptIn(ExperimentalCoroutinesApi::class)
class UseCaseDataBaseTest {

//    private val repositoryLocal = mock<RepositoryLocal>()
//    private val useCaseDataBase = UseCaseDataBase(repositoryLocal)

//
//    @Before
//    fun setup() {
//        repositoryLocal = mock()
//        useCaseDataBase = UseCaseDataBase(repositoryLocal)
//    }
//
//
//    @After
//    fun tearDown() {
//        Mockito.reset(repositoryLocal)
//    }

//    @Test
//    fun `should return the same sale items as in repository`() = runTest {
//        val testViewedGoodsList = listOf(
//            ViewedGoodsModel(
//                category = "test",
//                image_url = "test",
//                name = "test",
//                price = 0.0
//            )
//        )
//
//
//        val result = useCaseDataBase.getAllViewedGoods().test {
//            Mockito.`when`(useCaseDataBase.getAllViewedGoods().first())
//                .thenReturn(testViewedGoodsList)
//            assertThat(this.awaitItem()).isEqualTo(testViewedGoodsList)
//        }
//
//    }

//
//    @Test
//    fun `insert into favorite goods`() = runTest {
//        val repositoryLocal = mock<RepositoryLocal>()
//       val useCaseDataBase = UseCaseDataBase(repositoryLocal)
//        val testFavoriteGoods = FavoriteGoodsModel(
//            category = "category",
//            image_url = null,
//            name = "test",
//            price = 10.0
//        )
//        useCaseDataBase.insertIntoFavoriteGoods(testFavoriteGoods)
//        useCaseDataBase.getAllFavoriteGoods().test {
//
//            assertThat(this.awaitItem()).contains(testFavoriteGoods)
//        }
//
//    }
}