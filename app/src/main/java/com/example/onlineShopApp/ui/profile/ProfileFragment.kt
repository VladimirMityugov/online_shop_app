package com.example.onlineShopApp.ui.profile

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.chocky_development.onlineShopApp.databinding.FragmentProfileBinding
import com.example.onlineShopApp.presentation.ShopViewModel
import com.example.onlineShopApp.presentation.utility.Constants.JACK_SPARROW_IMAGE_URL
import com.example.onlineShopApp.presentation.utility.Constants.REQUIRED_PERMISSIONS
import com.example.onlineShopApp.presentation.utility.hasReadPermission
import com.example.onlineShopApp.ui.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

    private val photoPickerLauncher = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            shopViewModel.selectUserPhoto(uri)
        }
    }

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it.values.all { true }) {
                photoPickerLauncher
            }
        }


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


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            shopViewModel.selectedUserPhoto.collectLatest { uri ->
                if (uri != null) {
                    Glide
                        .with(userPhoto)
                        .load(uri)
                        .circleCrop()
                        .into(userPhoto)
                } else {
                    Glide
                        .with(userPhoto)
                        .load(JACK_SPARROW_IMAGE_URL)
                        .circleCrop()
                        .into(userPhoto)
                }
            }
        }


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
        if (requireContext().hasReadPermission()) {
            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        } else {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        if (shouldShowRequestPermissionRationale(REQUIRED_PERMISSIONS[0])) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Read permission dialog")
                .setMessage("To upload image, please accept read external storage permission")
                .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                    dialogLauncher()
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })
                .create()
                .show()
        } else {
            dialogLauncher()
        }
    }
    private fun dialogLauncher() {
        permissionLauncher.launch(REQUIRED_PERMISSIONS)
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