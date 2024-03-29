package com.example.onlineShopApp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chocky_development.onlineShopApp.R
import com.chocky_development.onlineShopApp.databinding.ItemBinding
import com.chocky_development.domain.models.sale_goods_model.SaleGoodsModel


class BrandsGoodsAdapter(
    private val onAddFavoriteButtonClick: (SaleGoodsModel) -> Unit,
    private val onAddToBasketButtonClick: (SaleGoodsModel) -> Unit,
    private val onBrandsItemCLick: (SaleGoodsModel) -> Unit
) :
    ListAdapter<SaleGoodsModel, BrandsGoodsViewHolder>(DiffUtilCallBackGoods()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsGoodsViewHolder {
        return BrandsGoodsViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onAddFavoriteButtonClick = onAddFavoriteButtonClick,
            onAddToBasketButtonClick = onAddToBasketButtonClick,
            onBrandsItemCLick = onBrandsItemCLick
        )
    }


    override fun onBindViewHolder(holder: BrandsGoodsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}


class BrandsGoodsViewHolder(
    private val binding: ItemBinding,
    val onAddFavoriteButtonClick: (SaleGoodsModel) -> Unit,
    val onAddToBasketButtonClick: (SaleGoodsModel) -> Unit,
    val onBrandsItemCLick: (SaleGoodsModel) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: SaleGoodsModel) {
        with(binding) {
            val remainder = (item.price * 100).toInt()
            goodsName.text = item.name
            goodsCategory.text = item.category
            goodsPrice.text = buildString {
                append("$ ") // currency could vary
                append(item.price.toInt())
                append(",")
                append(remainder.toString().takeLast(2))
            }
            discount.text = buildString {
                append(item.discount.toString())
                append("% off")
            }

            Glide
                .with(goodsPicture)
                .load(item.image_url)
                .error(R.drawable.apple_icon)
                .centerInside()
                .into(goodsPicture)

            addToBasketButton.setOnClickListener {
                onAddToBasketButtonClick(item)
            }

            addToFavoritesButton.setOnClickListener {
                onAddFavoriteButtonClick(item)
            }

            root.setOnClickListener {
                onBrandsItemCLick(item)
            }
        }
    }
}



