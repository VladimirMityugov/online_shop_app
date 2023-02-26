package com.example.onlineShopApp.presentation.paging_sources


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.onlineShopApp.domain.UseCaseNetwork

import javax.inject.Inject


//class FirstCustomSelectionPagingSource  @Inject constructor
//    (
//    private val useCaseNetwork: UseCaseNetwork ,
//    val country: Int,
//    val genre: Int
//) : PagingSource<Int, ItemCustom>() {
//
//    override fun getRefreshKey(state: PagingState<Int, ItemCustom>): Int = FIRST_PAGE
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemCustom> {
//        val page = params.key ?: FIRST_PAGE
//        return kotlin.runCatching {
//            useCaseRemote.getFirstCustomPagedSelection(page, country, genre)
//        }.fold(
//            onSuccess = {
//                LoadResult.Page(
//                    data = it,
//                    prevKey = null,
//                    nextKey = if (it.isEmpty()) null else page + 1
//                )
//            },
//            onFailure = { LoadResult.Error(throwable = Throwable(message = "There is no data to indicate")) }
//        )
//    }
//
//
//    companion object {
//        private const val FIRST_PAGE = 1
//    }
//}
