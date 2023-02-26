package com.example.onlineShopApp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chocky_development.onlineShopApp.databinding.FragmentGoodsDetailsBinding
import com.example.onlineShopApp.presentation.ShopViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GoodsDetailsFragment : Fragment() {

    private var _binding: FragmentGoodsDetailsBinding? = null
    private val binding get() = _binding!!



//    private val saleGoodsAdapter = GoodsAdapter(
//        onAddToBasketButtonClick = { goods -> onAddToBasketClick(goods) },
//    )






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



    }









    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}