package com.example.onlineShopApp.presentation.view_models

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocky_development.domain_.models.favorite_goods_model.FavoriteGoodsModel
import com.chocky_development.domain_.models.latest_goods_model.LatestGoodsModel
import com.chocky_development.domain_.models.sale_goods_model.SaleGoodsModel
import com.chocky_development.domain_.models.search_result_model.ResultsModel
import com.chocky_development.domain_.models.viewed_goods_model.ViewedGoodsModel
import com.chocky_development.domain_.use_cases.UseCaseDataBase
import com.chocky_development.domain_.use_cases.UseCaseNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val useCaseNetwork: UseCaseNetwork,
    private val useCaseDataBase: UseCaseDataBase
) : ViewModel() {

    // Variables

    private val _items = MutableStateFlow<List<SaleGoodsModel>>(emptyList())
    val items = _items.asStateFlow()

    private val _viewedItems = MutableStateFlow<List<LatestGoodsModel>>(emptyList())
    val viewedItems = _viewedItems.asStateFlow()

    private val _hidePassword = MutableStateFlow(true)
    val hidePassword = _hidePassword.asStateFlow()

    private val _selectedUserPhoto = MutableStateFlow<Uri?>(null)
    val selectedUserPhoto = _selectedUserPhoto.asStateFlow()

    private val _searchResults = MutableStateFlow<ResultsModel?>(null)
    val searchResults = _searchResults.asStateFlow()

    init {
        getSaleGoods()
        getViewedGoods()
        getSearchedResults()
    }

    // Network calls

    private fun getSaleGoods() {
        viewModelScope.launch {
            _items.value = useCaseNetwork.getSaleItems()
        }
    }

    private fun getViewedGoods() {
        viewModelScope.launch {
            _viewedItems.value = useCaseNetwork.getViewedItems()
        }
    }

    private fun getSearchedResults() {
        viewModelScope.launch {
            _searchResults.value = useCaseNetwork.getSearchResults()
        }
    }

//DataBaseQueries

    fun getAllViewedGoods(): Flow<List<ViewedGoodsModel>> = useCaseDataBase.getAllViewedGoods()

    suspend fun insertIntoViewedGoods(
        viewedGoodsModel: ViewedGoodsModel
    ) {
        viewModelScope.launch {
            useCaseDataBase.insertIntoViewedGoods(
                viewedGoodsModel
            )
        }
    }

    fun getAllFavoriteGoods(): Flow<List<FavoriteGoodsModel>> =
        useCaseDataBase.getAllFavoriteGoods()

    suspend fun insertIntoFavoriteGoods(
        favoriteGoodsModel: FavoriteGoodsModel
    ) {
        viewModelScope.launch {
            useCaseDataBase.insertIntoFavoriteGoods(
                favoriteGoodsModel
            )
        }
    }


    //Auxiliary

    fun changePasswordFieldSettings(hidePassword: Boolean) {
        viewModelScope.launch {
            _hidePassword.value = hidePassword
        }
    }


    fun selectUserPhoto(uri: Uri) {
        viewModelScope.launch {
            _selectedUserPhoto.value = uri
        }
    }
}




