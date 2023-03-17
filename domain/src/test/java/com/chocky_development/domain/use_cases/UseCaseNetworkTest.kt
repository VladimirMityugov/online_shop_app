package com.chocky_development.domain.use_cases


import com.chocky_development.domain.models.sale_goods_model.SaleGoodsModel
import com.chocky_development.domain.models.search_result_model.ResultsModel
import com.chocky_development.domain.repositories.RepositoryNetwork
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock


@OptIn(ExperimentalCoroutinesApi::class)
class UseCaseNetworkTest {

    private lateinit var repositoryNetwork: RepositoryNetwork
    private lateinit var useCaseNetwork: UseCaseNetwork


    @Before
    fun setup(){
        repositoryNetwork = mock()
        useCaseNetwork = UseCaseNetwork(repositoryNetwork)
    }


    @After
    fun tearDown(){
        Mockito.reset(repositoryNetwork)
    }

    @Test
    fun `should return the same sale items as in repository`() = runTest {
        val testSaleItemsList = listOf(
            SaleGoodsModel(
                category = "test",
                discount = 0,
                image_url = "test",
                name = "test",
                price = 0.0
            )
        )
        Mockito.`when`(repositoryNetwork.getSaleItems()).thenReturn(testSaleItemsList)
        val result = useCaseNetwork.getSaleItems()
        val expected = listOf(
            SaleGoodsModel(
                category = "test",
                discount = 0,
                image_url = "test",
                name = "test",
                price = 0.0
            )
        )
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return the same search results as in repository`() = runTest {
        val testSearchResults = ResultsModel(
            words = listOf("1", "2", "test")
        )
        Mockito.`when`(repositoryNetwork.getSearchResults()).thenReturn(testSearchResults)
        val result = useCaseNetwork.getSearchResults()
        val expected = ResultsModel(
            words = listOf("1", "2", "test")
        )
        assertThat(result).isEqualTo(expected)
    }
}