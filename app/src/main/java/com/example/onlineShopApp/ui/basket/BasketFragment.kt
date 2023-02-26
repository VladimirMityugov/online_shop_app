package com.example.onlineShopApp.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chocky_development.onlineShopApp.databinding.FragmentBasketBinding
import com.example.onlineShopApp.data.remote.sale_goods.SaleGoodsDto
import com.example.onlineShopApp.presentation.ShopViewModel
import com.example.onlineShopApp.presentation.adapters.GoodsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!





    private val shopViewModel: ShopViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun onItemClick(saleGoodsDto: SaleGoodsDto) {

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}