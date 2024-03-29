package com.example.onlineShopApp.ui.login


import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.chocky_development.onlineShopApp.databinding.FragmentLoginBinding
import com.example.onlineShopApp.presentation.view_models.ShopViewModel
import com.example.onlineShopApp.presentation.utility.Constants.PASSWORD
import com.example.onlineShopApp.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

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


        val firstName = binding.firstNameField
        val password = binding.passwordField
        val eyeIcon = binding.eyeIcon

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

        binding.loginButton.setOnClickListener {
            val name =
                firstName.text.toString()
            when {
                name.isEmpty() ->
                    Toast.makeText(
                        requireContext(),
                        "Please, enter name",
                        Toast.LENGTH_SHORT
                    ).show()
                password.text.toString().isEmpty() ->
                    Toast.makeText(
                        requireContext(),
                        "Please, enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                else -> {
                    onLoginButtonCLick(
                        email = name,
                        password = PASSWORD)
                }
            }
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
