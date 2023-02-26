package com.example.onlineShopApp.presentation.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chocky_development.onlineShopApp.databinding.CategoriesItemBinding
import com.example.onlineShopApp.presentation.utility.CategoriesDataModel


class CategoriesAdapter(
    private val onCategoryItemCLick: (CategoriesDataModel) -> Unit
) :
    ListAdapter<CategoriesDataModel, CategoriesViewHolder>(DiffUtilCallBackCategories()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            CategoriesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onCategoryItemCLick = onCategoryItemCLick
        )
    }


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}


class CategoriesViewHolder(
    private val binding: CategoriesItemBinding,
    val onCategoryItemCLick: (CategoriesDataModel) -> Unit

) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CategoriesDataModel) {
        with(binding) {

            categoryTitle.text = item.category_name

            Glide
                .with(categoryPicture.context)
                .load(item.category_picture)
                .into(categoryPicture)


            root.setOnClickListener {
                onCategoryItemCLick(item)
            }


        }
    }
}

class DiffUtilCallBackCategories : DiffUtil.ItemCallback<CategoriesDataModel>() {
    override fun areItemsTheSame(
        oldItem: CategoriesDataModel,
        newItem: CategoriesDataModel
    ): Boolean {
        return oldItem.category_name == newItem.category_name
    }

    override fun areContentsTheSame(
        oldItem: CategoriesDataModel,
        newItem: CategoriesDataModel
    ): Boolean {
        return oldItem == newItem
    }
}

