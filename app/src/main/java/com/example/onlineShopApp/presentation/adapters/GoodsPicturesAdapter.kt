package com.example.onlineShopApp.presentation.adapters

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.FrameLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.chocky_development.onlineShopApp.R
import com.chocky_development.onlineShopApp.databinding.GoodsPicturesItemBinding
import com.example.onlineShopApp.presentation.utility.Constants.ELEVATION_VALUE_FOR_ANIMATION
import com.example.onlineShopApp.presentation.utility.Constants.SCALE_VALUE_FOR_ANIMATION
import com.example.onlineShopApp.presentation.utility.Constants.TRANSLATION_VALUE_FINAL_FOR_ANIMATION
import com.example.onlineShopApp.presentation.utility.Constants.TRANSLATION_VALUE_INITIAL_FOR_ANIMATION

class GoodsPicturesAdapter(
    private val onPictureClick: (String) -> Unit
) :
    ListAdapter<String, PicturesViewHolder>(DiffUtilCallBackPictures()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        return PicturesViewHolder(
            GoodsPicturesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        val goodsPictureContainer =
            holder.itemView.findViewById<FrameLayout>(R.id.goods_picture_container)

        holder.itemView.setOnClickListener {
            SELECTED_ITEM = holder.bindingAdapterPosition
            notifyItemChanged(position)
        }

        if (position == SELECTED_ITEM) {
            onPictureClick(item)
            holder.animateItem(goodsPictureContainer)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == SELECTED_ITEM) LARGE
        else NORMAL
    }


    companion object {
        const val LARGE = 1
        const val NORMAL = 0
        private var SELECTED_ITEM = RecyclerView.NO_POSITION
    }


}


class PicturesViewHolder(
    private val binding: GoodsPicturesItemBinding
) :
    ViewHolder(binding.root) {
    fun bind(item: String) {
        with(binding) {

            Glide
                .with(goodsPicture)
                .load(item)
                .centerCrop()
                .into(goodsPicture)

        }
    }

    fun animateItem(view: View) {
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", SCALE_VALUE_FOR_ANIMATION)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", SCALE_VALUE_FOR_ANIMATION)
        val translationY = PropertyValuesHolder.ofFloat(
            "translationY",
            TRANSLATION_VALUE_INITIAL_FOR_ANIMATION,
            TRANSLATION_VALUE_FINAL_FOR_ANIMATION
        )
        val elevation = PropertyValuesHolder.ofFloat("elevation", ELEVATION_VALUE_FOR_ANIMATION)
        val animator =
            ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY, translationY, elevation)
        animator.duration = 1000L
        animator.interpolator = BounceInterpolator()
        animator.start()
    }
}


class DiffUtilCallBackPictures : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

