package com.example.onlineShopApp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chocky_development.domain_.models.sale_goods_model.SaleGoodsModel
import com.chocky_development.onlineShopApp.databinding.FragmentFavoritesBinding
import com.example.onlineShopApp.presentation.view_models.ShopViewModel


class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val shopViewModel: ShopViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onItemClick(saleGoodsModel: SaleGoodsModel) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}