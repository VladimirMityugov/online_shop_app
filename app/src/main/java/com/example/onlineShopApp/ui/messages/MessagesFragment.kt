package com.example.onlineShopApp.ui.messages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chocky_development.onlineShopApp.databinding.FragmentMessagesBinding
import com.example.onlineShopApp.data.remote.sale_goods.SaleGoodsDto
import com.example.onlineShopApp.presentation.ShopViewModel
import com.example.onlineShopApp.presentation.adapters.GoodsAdapter
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "MESSAGES"
@AndroidEntryPoint
class MessagesFragment : Fragment() {

    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!





    private val shopViewModel: ShopViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun onItemClick(saleGoodsDto: SaleGoodsDto) {
        Log.d(TAG, "Goods with name : ${saleGoodsDto.name} is clicked")
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}