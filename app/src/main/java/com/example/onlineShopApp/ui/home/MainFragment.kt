package com.example.onlineShopApp.ui.home

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chocky_development.onlineShopApp.R
import com.chocky_development.onlineShopApp.databinding.FragmentMainBinding
import com.example.onlineShopApp.data.remote.latest_goods.LatestGoodsDto
import com.example.onlineShopApp.data.remote.sale_goods.SaleGoodsDto
import com.example.onlineShopApp.presentation.ShopViewModel
import com.example.onlineShopApp.presentation.adapters.BrandsGoodsAdapter
import com.example.onlineShopApp.presentation.adapters.CategoriesAdapter
import com.example.onlineShopApp.presentation.adapters.GoodsAdapter
import com.example.onlineShopApp.presentation.adapters.ViewedGoodsAdapter
import com.example.onlineShopApp.presentation.utility.CategoriesDataModel
import com.example.onlineShopApp.presentation.utility.Constants.JACK_SPARROW_IMAGE_URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewedRecyclerView: RecyclerView
    private lateinit var saleRecyclerView: RecyclerView
    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var brandsRecyclerView: RecyclerView
    private lateinit var menu: AppCompatImageView
    private lateinit var avatar: AppCompatImageView
    private lateinit var mainTitle: AppCompatTextView
    private lateinit var locationChangeButton: AppCompatImageView
    private lateinit var viewAllLatest: AppCompatTextView
    private lateinit var viewAllSale: AppCompatTextView
    private lateinit var autoCompleteTextView:AutoCompleteTextView
    private var categoriesDataModel = CategoriesDataModel(null, null)

    private val saleGoodsAdapter = GoodsAdapter(
        onAddToBasketButtonClick = { goods -> onAddToBasketClick(goods) },
        onAddFavoriteButtonClick = { goods -> onAddToFavoriteClick(goods) },
        onSaleItemCLick = { goods -> onSaleItemClick(goods) }
    )


    private val viewedGoodsAdapter = ViewedGoodsAdapter(
        onGoodsItemClick = { goods -> onViewedItemClick(goods) },
        onAddToBasketClick = { goods -> onAddViewedToBasketClick(goods) }
    )

    private val categoriesAdapter = CategoriesAdapter(
        onCategoryItemCLick = { category -> onCategoryItemClick(category) }
    )

    private val brandsGoodsAdapter = BrandsGoodsAdapter(
        onAddToBasketButtonClick = { goods -> onAddToBasketClick(goods) },
        onAddFavoriteButtonClick = { goods -> onAddToFavoriteClick(goods) },
        onBrandsItemCLick = { goods -> onBrandsItemClick(goods) }
    )


    private val shopViewModel: ShopViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewedRecyclerView = binding.latestRecyclerView
        saleRecyclerView = binding.saleRecyclerView
        categoriesRecyclerView = binding.categoriesRecyclerView
        brandsRecyclerView = binding.brandsRecyclerView

        viewedRecyclerView.adapter = viewedGoodsAdapter
        saleRecyclerView.adapter = saleGoodsAdapter
        categoriesRecyclerView.adapter = categoriesAdapter
        brandsRecyclerView.adapter = brandsGoodsAdapter


        menu = binding.menu
        avatar = binding.avatar
        mainTitle = binding.mainTitle
        locationChangeButton = binding.locationChangeButton
        viewAllLatest = binding.showAllLatest
        viewAllSale = binding.showAllSale
        autoCompleteTextView = binding.searchView


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            shopViewModel.searchResults.collectLatest { searchResults ->
                if(searchResults !=null){
                    val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1, searchResults.words)
                    autoCompleteTextView.setAdapter(adapter)
                }
            }
        }

        viewAllLatest.setOnClickListener {
            onViewAllLatestClick()
        }

        viewAllSale.setOnClickListener {
            onViewAllSaleCLick()
        }

        locationChangeButton.setOnClickListener {
            onLocationChangeClick()
        }


        menu.setOnClickListener {
            onMenuClick()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            shopViewModel.selectedUserPhoto.collectLatest { uri ->
                if (uri != null) {
                    Glide
                        .with(avatar)
                        .load(uri)
                        .circleCrop()
                        .into(avatar)
                } else {
                    Glide
                        .with(avatar)
                        .load(JACK_SPARROW_IMAGE_URL)
                        .circleCrop()
                        .into(avatar)
                }
            }
        }

        val mainTitleSpan = SpannableString(mainTitle.text)
        val startIndex = mainTitleSpan.indexOf("bata")
        val textLength = "bata".length
        mainTitleSpan.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.myBlue, resources.newTheme())),
            startIndex,
            startIndex + textLength,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        mainTitle.text = mainTitleSpan



        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            shopViewModel.viewedItems.collectLatest { viewedItems ->
                shopViewModel.items.collectLatest { saleItems ->
                    if (viewedItems != null && saleItems != null) {
                        viewedGoodsAdapter.submitList(viewedItems.latest)
                        saleGoodsAdapter.submitList(saleItems.flash_sale)
                        brandsGoodsAdapter.submitList(saleItems.flash_sale)
                    }
                }
            }
        }

        categoriesAdapter.submitList(categoriesDataModel.getItemsList())

        //Display items, previously saved into DB

//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            shopViewModel.getAllViewedGoods().collectLatest { allViewed ->
//                viewedGoodsAdapter.submitList(allViewed)
//            }
//        }


    }


    private fun onCategoryItemClick(category: CategoriesDataModel) {
        Toast.makeText(
            requireContext(),
            "Category ${category.category_name} is clicked",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onViewAllSaleCLick() {
        Toast.makeText(
            requireContext(),
            "Clicked on view all sale",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onViewAllLatestClick() {
        Toast.makeText(
            requireContext(),
            "Clicked on view all latest",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onLocationChangeClick() {
        Toast.makeText(
            requireContext(),
            "Clicked on location change",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onMenuClick() {
        Toast.makeText(
            requireContext(),
            "Clicked on menu",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onAddToFavoriteClick(goods: SaleGoodsDto) {
        Toast.makeText(
            requireContext(),
            "Add to favorites goods with name : ${goods.name}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onAddToBasketClick(goods: SaleGoodsDto) {
        Toast.makeText(
            requireContext(),
            "Add to basket goods with name  : ${goods.name}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onViewedItemClick(goods: LatestGoodsDto) {
        Toast.makeText(
            requireContext(),
            "Viewed goods with name  : ${goods.name} is clicked",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onAddViewedToBasketClick(goods: LatestGoodsDto) {
        Toast.makeText(
            requireContext(),
            "Add to basket goods with name  : ${goods.name}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onSaleItemClick(goods: SaleGoodsDto) {
        shopViewModel.getGoodsDetails()
        shopViewModel.selectGoods(goods.name)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            shopViewModel.insertIntoViewedGoods(
                category = goods.category,
                image_url = goods.image_url,
                name = goods.name,
                price = goods.price
            )
        }
        findNavController().navigate(R.id.action_navigation_home_to_goodsDetailsFragment)
    }

    private fun onBrandsItemClick(goods: SaleGoodsDto) {
        Toast.makeText(
            requireContext(),
            "Brands goods with name  : ${goods.name} is clicked",
            Toast.LENGTH_SHORT
        ).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
