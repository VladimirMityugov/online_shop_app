package com.example.onlineShopApp.presentation.utility

import com.example.onlineShopApp.presentation.utility.Constants.EN
import com.example.onlineShopApp.presentation.utility.Constants.RU
import java.util.*

data class CategoriesDataModel(
    val category_name: String?,
    val category_picture: Int?
) {
    fun getItemsList(): List<CategoriesDataModel> {
        val list = mutableListOf<CategoriesDataModel>()
        if (getLanguage() == RU) {
            Categories.categoriesInRussian.entries.forEach {
                list.add(
                    CategoriesDataModel(
                        category_name = it.key,
                        category_picture = it.value
                    )
                )
            }
        } else {
            Categories.categories.entries.forEach {
                list.add(
                    CategoriesDataModel(
                        category_name = it.key,
                        category_picture = it.value
                    )
                )
            }
        }
        return list
    }

    private fun getLanguage(): String {
        return if (Locale.getDefault() == Locale("ru", "RU")) RU
        else EN
    }

}
