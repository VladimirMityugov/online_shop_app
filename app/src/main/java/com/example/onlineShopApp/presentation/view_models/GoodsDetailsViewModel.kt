package com.example.onlineShopApp.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocky_development.domain.models.favorite_goods_model.FavoriteGoodsModel
import com.chocky_development.domain.models.goods_details_model.GoodsDetailsModel
import com.chocky_development.domain.use_cases.UseCaseDataBase
import com.chocky_development.domain.use_cases.UseCaseNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoodsDetailsViewModel @Inject constructor(
    private val useCaseNetwork: UseCaseNetwork,
    private val useCaseDataBase: UseCaseDataBase
): ViewModel() {

    private val _goodsDetails = MutableStateFlow<GoodsDetailsModel?>(null)
    val goodsDetails = _goodsDetails.asStateFlow()

    private val _currentGoodsQuantity = MutableStateFlow(0)
    val currentGoodsQuantity = _currentGoodsQuantity.asStateFlow()

    private val _selectedColor = MutableStateFlow<String?>(null)
    val selectedColor = _selectedColor.asStateFlow()

    private val _selectedGoods = MutableStateFlow<String?>(null)
    val selectedGoods = _selectedGoods.asStateFlow()

    private val _selectedPicture = MutableStateFlow<String?>(null)
    val selectedPicture = _selectedPicture.asStateFlow()



    // Network calls
    fun getGoodsDetails() {
        viewModelScope.launch {
            _goodsDetails.value = useCaseNetwork.getGoodsDetails()
        }
    }


    //Database queries
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

    fun incrementGoodsQuantity() {
        viewModelScope.launch {
            val currentQuantityStatus = _currentGoodsQuantity.value
            _currentGoodsQuantity.value = currentQuantityStatus + 1
        }
    }

    fun decrementGoodsQuantity() {
        viewModelScope.launch {
            val currentQuantityStatus = _currentGoodsQuantity.value
            if (currentQuantityStatus > 0) {
                _currentGoodsQuantity.value = currentQuantityStatus - 1
            }
        }
    }

    fun selectColor(color: String) {
        viewModelScope.launch {
            _selectedColor.value = color
        }
    }

    fun selectGoods(goodsName: String) {
        viewModelScope.launch {
            _selectedGoods.value = goodsName
        }
    }

    fun selectPicture(picture: String) {
        viewModelScope.launch {
            _selectedPicture.value = picture
        }
    }


}