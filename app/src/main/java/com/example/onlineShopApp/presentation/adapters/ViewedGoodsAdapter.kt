package com.example.onlineShopApp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chocky_development.domain_.models.latest_goods_model.LatestGoodsModel
import com.chocky_development.onlineShopApp.databinding.ViewedItemBinding



class ViewedGoodsAdapter(
    private val onGoodsItemClick: (LatestGoodsModel) -> Unit,
    private val onAddToBasketClick: (LatestGoodsModel) -> Unit
) :
    ListAdapter<LatestGoodsModel, ViewedGoodsViewHolder>(DiffUtilCallBackViewedGoods()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewedGoodsViewHolder {
        return ViewedGoodsViewHolder(
            ViewedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick = onGoodsItemClick,
            onAddToBasketClick = onAddToBasketClick
        )
    }


    override fun onBindViewHolder(holder: ViewedGoodsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}


class ViewedGoodsViewHolder(
    private val binding: ViewedItemBinding,
    val onItemClick: (LatestGoodsModel) -> Unit,
    val onAddToBasketClick: (LatestGoodsModel) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: LatestGoodsModel) {
        with(binding) {
            val remainder = (item.price * 100).toInt()
            goodsCategory.text = item.category
            goodsName.text = item.name
            goodsPrice.text = buildString {
                append("$ ") // currency could vary
                append(item.price.toInt())
                append(",")
                append(remainder.toString().takeLast(2))
            }

            Glide
                .with(goodsPicture.context)
                .load(item.image_url)
                .centerCrop()
                .into(goodsPicture)

            addToBasketButton.setOnClickListener {
                onAddToBasketClick(item)
            }

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}

class DiffUtilCallBackViewedGoods : DiffUtil.ItemCallback<LatestGoodsModel>() {
    override fun areItemsTheSame(oldItem: LatestGoodsModel, newItem: LatestGoodsModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: LatestGoodsModel, newItem: LatestGoodsModel): Boolean {
        return oldItem == newItem
    }
}

