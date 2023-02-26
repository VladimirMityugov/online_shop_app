package com.example.onlineShopApp.presentation.utility

data class CategoriesDataModel(
    val category_name: String?,
    val category_picture: Int?
){
    fun getItemsList(): List<CategoriesDataModel> {
        val list = mutableListOf<CategoriesDataModel>()
        Categories.categories.entries.forEach {
            list.add(
                CategoriesDataModel(
                    category_name = it.key,
                    category_picture = it.value
                )
            )
        }
        return list
    }
}
