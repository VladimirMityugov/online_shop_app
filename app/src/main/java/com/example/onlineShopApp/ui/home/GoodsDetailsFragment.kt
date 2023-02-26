package com.example.onlineShopApp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.chocky_development.onlineShopApp.databinding.FragmentGoodsDetailsBinding
import com.example.onlineShopApp.presentation.ShopViewModel
import dagger.hilt.android.AndroidEntryPoint


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
    private lateinit var viewPager: ViewPager2
    private lateinit var minusButton: AppCompatButton
    private lateinit var plusButton: AppCompatButton
    private lateinit var addToCart: AppCompatButton
    private lateinit var amount: AppCompatTextView


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
        viewPager = binding.viewPager
        minusButton = binding.minusButton
        plusButton = binding.plusButton
        addToCart = binding.addToCartButton
        amount = binding.amount


    }









    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}