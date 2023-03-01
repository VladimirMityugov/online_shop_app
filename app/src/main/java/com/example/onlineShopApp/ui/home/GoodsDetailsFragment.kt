package com.example.onlineShopApp.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chocky_development.onlineShopApp.R
import com.chocky_development.onlineShopApp.databinding.FragmentGoodsDetailsBinding
import com.example.onlineShopApp.presentation.ShopViewModel
import com.example.onlineShopApp.presentation.adapters.GoodsPicturesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class GoodsDetailsFragment : Fragment() {

    private var _binding: FragmentGoodsDetailsBinding? = null
    private val binding get() = _binding!!


    private lateinit var backButton: AppCompatImageView
    private lateinit var goodsPicture: AppCompatImageView
    private lateinit var favoritesButton: AppCompatImageView
    private lateinit var shareButton: AppCompatImageView
    private lateinit var goodsName: AppCompatTextView
    private lateinit var goodsPrice: AppCompatTextView
    private lateinit var goodsDescription: AppCompatTextView
    private lateinit var goodsRating: AppCompatTextView
    private lateinit var goodsReviews: AppCompatTextView
    private lateinit var containerForColorSamples: LinearLayout
    private lateinit var goodsPicturesRecyclerView: RecyclerView
    private lateinit var minusButton: AppCompatButton
    private lateinit var plusButton: AppCompatButton
    private lateinit var addToCart: AppCompatButton
    private lateinit var amount: AppCompatTextView
    private lateinit var goodsQuantity: AppCompatTextView

    private val goodsPicturesAdapter = GoodsPicturesAdapter(
        onPictureClick = { picture -> onGoodsPictureClick(picture) }
    )


    private val shopViewModel: ShopViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoodsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton = binding.backButton
        goodsPicture = binding.goodsPicture
        favoritesButton = binding.favoritesButton
        shareButton = binding.shareButton
        goodsName = binding.goodsName
        goodsPrice = binding.goodsPrice
        goodsDescription = binding.goodsDescription
        goodsRating = binding.goodsRating
        goodsReviews = binding.goodsReviewsText
        containerForColorSamples = binding.containerForColorSamples
        goodsPicturesRecyclerView = binding.goodsPicturesRecyclerView
        minusButton = binding.minusButton
        plusButton = binding.plusButton
        addToCart = binding.addToCartButton
        amount = binding.amount
        goodsQuantity = binding.goodsQuantity


        goodsPicturesRecyclerView.adapter = goodsPicturesAdapter


        backButton.setOnClickListener {
            onBackButtonClick()
        }

        favoritesButton.setOnClickListener {
            onFavoritesButtonClick()
        }

        shareButton.setOnClickListener {
            onShareButtonCLick()
        }

        plusButton.setOnClickListener {
            onPlusButtonClick()
        }

        minusButton.setOnClickListener {
            onMinusButtonClick()
        }

        addToCart.setOnClickListener {
            onAddToCartButtonClick()
        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            shopViewModel.goodsDetails.collectLatest { goodsDetails ->
                if (goodsDetails != null) {
                    for (color in goodsDetails.colors) {
                        containerForColorSamples.addView(inflateColorSampleView(color))
                    }

                    val convertedAmount = convertAmount(goodsDetails.price)
                    goodsPrice.text = buildString {
                        append("$ ")
                        append(convertedAmount)
                    }

                    goodsPicturesAdapter.submitList(goodsDetails.image_urls)


                    goodsName.text = goodsDetails.name
                    goodsDescription.text = goodsDetails.description
                    goodsRating.text = goodsDetails.rating.toString()
                    goodsReviews.text = convertReviews(goodsDetails.number_of_reviews)

                    Glide
                        .with(goodsPicture)
                        .load(goodsDetails.image_urls.firstOrNull())
                        .centerCrop()
                        .into(goodsPicture)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            shopViewModel.currentGoodsQuantity.collectLatest { quantity ->
                shopViewModel.goodsDetails.collectLatest { goodsDetails ->
                    if (goodsDetails != null) {
                        goodsQuantity.text = quantity.toString()
                        val total = quantity * goodsDetails.price
                        val convertedAmount = convertAmount(total)
                        amount.text = buildString {
                            append("$ ")
                            append(convertedAmount)
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            shopViewModel.selectedPicture.collectLatest { picture ->
                if (picture != null) {
                    Glide
                        .with(goodsPicture)
                        .load(picture)
                        .centerCrop()
                        .into(goodsPicture)
                }
            }
        }

    }


    private fun onGoodsPictureClick(picture: String) {
        shopViewModel.selectPicture(picture)
    }

    private fun onAddToCartButtonClick() {
        if (shopViewModel.currentGoodsQuantity.value == 0) {
            Toast.makeText(requireContext(), "Please, increase goods quantity", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(requireContext(), "Go to payment screen", Toast.LENGTH_SHORT).show()
        }
    }

    private fun convertAmount(amount: Double): String {
        val remainder = (amount * 100).toInt()
        val convertedAmount = buildString {
            append(amount.toInt())
            append(",")
            append(remainder.toString().takeLast(2))
        }
        return convertedAmount
    }

    private fun convertReviews(reviewsQuantity: Int): String {
        return buildString {
            append("( ")
            append(reviewsQuantity)
            append(" reviews ")
            append(")")
        }
    }

    private fun onMinusButtonClick() {
        shopViewModel.decrementGoodsQuantity()
    }

    private fun onPlusButtonClick() {
        shopViewModel.incrementGoodsQuantity()
    }

    private fun onShareButtonCLick() {
        Toast.makeText(requireContext(), "Share button is clicked", Toast.LENGTH_SHORT).show()
    }

    private fun onFavoritesButtonClick() {
        Toast.makeText(requireContext(), "Favorites button is clicked", Toast.LENGTH_SHORT).show()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            shopViewModel.goodsDetails.collectLatest {goodsDetails ->
                shopViewModel.selectedGoods.collectLatest { selectedGoods ->
                    if(goodsDetails!=null && goodsDetails.name == selectedGoods){
                        shopViewModel.insertIntoFavoriteGoods(
                            category = null,
                            image_url = goodsDetails.image_urls.firstOrNull(),
                            name = goodsDetails.name,
                            price = goodsDetails.price
                        )
                    }
                }
            }
        }
    }

    private fun onBackButtonClick() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun inflateColorSampleView(color: String): View {
        val colorSampleView = layoutInflater.inflate(R.layout.color_sample_layout, null)
        val colorSample = colorSampleView.findViewById<ImageView>(R.id.color_sample)
        colorSample.setBackgroundColor(Color.parseColor(color))
        colorSampleView.setOnClickListener {
            Toast.makeText(requireContext(), "Color $color is clicked", Toast.LENGTH_SHORT).show()
            shopViewModel.selectColor(color)
        }
        return colorSampleView
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}