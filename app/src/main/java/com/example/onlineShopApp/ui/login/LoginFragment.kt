package com.example.onlineShopApp.ui.login


import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.chocky_development.onlineShopApp.R
import com.chocky_development.onlineShopApp.databinding.FragmentLoginBinding
import com.example.onlineShopApp.presentation.ShopViewModel
import com.example.onlineShopApp.presentation.utility.Constants
import com.example.onlineShopApp.presentation.utility.Constants.PASSWORD
import com.example.onlineShopApp.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var firstName: AppCompatEditText
    private lateinit var password: AppCompatEditText
    private lateinit var loginButton: AppCompatButton
    private lateinit var eyeIcon: AppCompatImageView
    private lateinit var authentification: FirebaseAuth
    private var hidePassword = true


    private val viewModel: ShopViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eyeIcon = binding.eyeIcon
        firstName = binding.firstNameField
        password = binding.passwordField
        loginButton = binding.loginButton
        authentification = FirebaseAuth.getInstance()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.hidePassword.collectLatest { hidePassword ->
                eyeIcon.isActivated = !hidePassword
            }
        }

        eyeIcon.setOnClickListener {
            if (hidePassword) {
                hidePassword = false
                password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                viewModel.changePasswordFieldSettings(hidePassword)
            } else {
                hidePassword = true
                password.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                viewModel.changePasswordFieldSettings(hidePassword)
            }
        }

        loginButton.setOnClickListener {
            val firstName =
                firstName.text.toString()
            onLoginButtonCLick(firstName, PASSWORD)

        }

    }

    private fun onLoginButtonCLick(email: String, password: String) {

        authentification.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    requireActivity().finish()
                } else {
                    Toast.makeText(
                        requireContext(), "Please check introduced data or create an account",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}
