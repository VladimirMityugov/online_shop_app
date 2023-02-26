package com.example.onlineShopApp.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineShopApp.data.local.entities.ViewedGoods
import com.example.onlineShopApp.data.remote.latest_goods.Latest
import com.example.onlineShopApp.data.remote.sale_goods.Sale
import com.example.onlineShopApp.domain.UseCaseDataBase
import com.example.onlineShopApp.domain.UseCaseNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "VIEW_MODEL"

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val useCaseNetwork: UseCaseNetwork,
    private val useCaseDataBase: UseCaseDataBase
) : ViewModel() {

    // Variables

    private val _items = MutableStateFlow<Sale?>(null)
    val items = _items.asStateFlow()

    private val _viewedItems = MutableStateFlow<Latest?>(null)
    val viewedItems = _viewedItems.asStateFlow()

    private val _hidePassword = MutableStateFlow(true)
    val hidePassword = _hidePassword.asStateFlow()

    private val _currentUserName = MutableStateFlow<String?>(null)
    val currentUserName = _currentUserName.asStateFlow()


    init {
        getSaleGoods()
        getViewedGoods()
    }

    // Network calls

    private fun getSaleGoods() {
        viewModelScope.launch {
            _items.value = useCaseNetwork.getItems()
        }
    }

    private fun getViewedGoods() {
        viewModelScope.launch {
            _viewedItems.value = useCaseNetwork.getViewedItems()
        }
    }

//DataBaseQueries

    fun getAllViewedGoods(): Flow<List<ViewedGoods>> = useCaseDataBase.getAllViewedGoods()

    suspend fun insertIntoViewedGoods(
        category: String,
        image_url: String,
        name: String,
        price: Double
    ) {
        viewModelScope.launch {
            useCaseDataBase.insertIntoViewedGoods(
                viewedGoods = ViewedGoods(
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

    fun setUserName(name: String) {
        viewModelScope.launch {
            _currentUserName.value = name
        }
    }

    companion object {

    }
}




