package com.example.onlineShopApp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.chocky_development.onlineShopApp.databinding.FragmentProfileBinding
import com.example.onlineShopApp.data.remote.sale_goods.SaleGoodsDto
import com.example.onlineShopApp.presentation.ShopViewModel
import com.example.onlineShopApp.presentation.adapters.GoodsAdapter
import com.example.onlineShopApp.presentation.utility.Constants
import com.example.onlineShopApp.presentation.utility.Constants.JACK_SPARROW_IMAGE_URL
import com.example.onlineShopApp.presentation.utility.Constants.JACK_SPARROW_NAME
import com.example.onlineShopApp.ui.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var changePhoto: AppCompatTextView
    private lateinit var logOutSign: AppCompatImageView
    private lateinit var logOutText: AppCompatTextView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userName: AppCompatTextView
    private lateinit var userPhoto: AppCompatImageView
    private lateinit var backButton: AppCompatImageView

    private val shopViewModel: ShopViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changePhoto = binding.changePhoto
        logOutSign = binding.logoutPicture
        logOutText = binding.logoutText
        firebaseAuth = FirebaseAuth.getInstance()
        userName = binding.userName
        userPhoto = binding.avatar
        backButton = binding.backButton

        Glide
            .with(this)
            .load(JACK_SPARROW_IMAGE_URL)
            .circleCrop()
            .into(userPhoto)



        userName.text = firebaseAuth.currentUser?.displayName


        logOutText.setOnClickListener {
            onSignOutClick()
        }

        logOutSign.setOnClickListener {
            onSignOutClick()
        }

        changePhoto.setOnClickListener {
            onChangePhotoClick()
        }

        backButton.setOnClickListener {
            onBackButtonClick()
        }


    }

    private fun onBackButtonClick() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun onChangePhotoClick() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivity(intent)
//registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
//
//}
    }

    private fun onSignOutClick() {
        firebaseAuth.signOut()
        startActivity(Intent(requireContext(), LoginActivity::class.java))
        requireActivity().finish()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}