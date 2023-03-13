package com.example.onlineShopApp.presentation.view_models


import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineShopApp.domain.models.favorite_goods_model.FavoriteGoodsModel
import com.example.onlineShopApp.domain.models.latest_goods_model.LatestModel
import com.example.onlineShopApp.domain.models.sale_goods_model.SaleModel
import com.example.onlineShopApp.domain.models.goods_details_model.GoodsDetailsModel
import com.example.onlineShopApp.domain.models.search_result_model.ResultsModel
import com.example.onlineShopApp.domain.models.viewed_goods_model.ViewedGoodsModel
import com.example.onlineShopApp.domain.use_cases.UseCaseDataBase
import com.example.onlineShopApp.domain.use_cases.UseCaseNetwork
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

    private val _items = MutableStateFlow<SaleModel?>(null)
    val items = _items.asStateFlow()

    private val _viewedItems = MutableStateFlow<LatestModel?>(null)
    val viewedItems = _viewedItems.asStateFlow()

    private val _hidePassword = MutableStateFlow(true)
    val hidePassword = _hidePassword.asStateFlow()

    private val _goodsDetails = MutableStateFlow<GoodsDetailsModel?>(null)
    val goodsDetails = _goodsDetails.asStateFlow()

    private val _currentGoodsQuantity = MutableStateFlow(0)
    val currentGoodsQuantity = _currentGoodsQuantity.asStateFlow()

    private val _selectedColor = MutableStateFlow<String?>(null)
    val selectedColor = _selectedColor.asStateFlow()

    private val _selectedPicture = MutableStateFlow<String?>(null)
    val selectedPicture = _selectedPicture.asStateFlow()

    private val _selectedUserPhoto = MutableStateFlow<Uri?>(null)
    val selectedUserPhoto = _selectedUserPhoto.asStateFlow()

    private val _searchResults = MutableStateFlow<ResultsModel?>(null)
    val searchResults = _searchResults.asStateFlow()

    private val _selectedGoods = MutableStateFlow<String?>("")
    val selectedGoods = _selectedGoods.asStateFlow()

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

    fun getGoodsDetails() {
        viewModelScope.launch {
            _goodsDetails.value = useCaseNetwork.getGoodsDetails()
        }
    }

    private fun getSearchedResults(){
        viewModelScope.launch {
            _searchResults.value = useCaseNetwork.getSearchResults()
        }
    }

//DataBaseQueries

    fun getAllViewedGoods(): Flow<List<ViewedGoodsModel>> = useCaseDataBase.getAllViewedGoods()

    suspend fun insertIntoViewedGoods(
        category: String,
        image_url: String,
        name: String,
        price: Double
    ) {
        viewModelScope.launch {
            useCaseDataBase.insertIntoViewedGoods(
                viewedGoodsModel = ViewedGoodsModel(
                    category = category,
                    image_url = image_url,
                    name = name,
                    price = price
                )
            )
        }
    }

    fun getAllFavoriteGoods(): Flow<List<FavoriteGoodsModel>> = useCaseDataBase.getAllFavoriteGoods()

    suspend fun insertIntoFavoriteGoods(
        category: String?,
        image_url: String?,
        name: String,
        price: Double
    ) {
        viewModelScope.launch {
            useCaseDataBase.insertIntoFavoriteGoods(
                favoriteGoodsModel = FavoriteGoodsModel(
                    category = category,
                    image_url = image_url,
                    name = name,
                    price = price
                )
            )
        }
    }


    //Auxiliary

    fun changePasswordFieldSettings(hidePassword: Boolean) {
        viewModelScope.launch {
            _hidePassword.value = hidePassword
        }
    }

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

    fun selectColor(color:String){
        viewModelScope.launch {
            _selectedColor.value = color
        }
    }

    fun selectPicture(picture:String){
        viewModelScope.launch {
            _selectedPicture.value = picture
        }
    }

    fun selectGoods(goodsName:String){
        viewModelScope.launch {
            _selectedGoods.value = goodsName
        }
    }

    fun selectUserPhoto(uri: Uri){
        viewModelScope.launch {
            _selectedUserPhoto.value = uri
        }
    }
}




