package com.example.onlineShopApp.ui.login


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.activityViewModels
import com.chocky_development.onlineShopApp.R
import com.chocky_development.onlineShopApp.databinding.FragmentSigninBinding
import com.example.onlineShopApp.presentation.view_models.ShopViewModel
import com.example.onlineShopApp.presentation.utility.Constants.PASSWORD
import com.example.onlineShopApp.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest


private const val TAG = "SIGN_IN_FRAGMENT"

class SignInFragment : Fragment() {

    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    private lateinit var firstName: AppCompatEditText
    private lateinit var lastName: AppCompatEditText
    private lateinit var email: AppCompatEditText
    private lateinit var authentification: FirebaseAuth
    private lateinit var username: String

    private val viewModel: ShopViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSigninBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstName = binding.firstNameField
        lastName = binding.lastNameField
        email = binding.emailField

        authentification = FirebaseAuth.getInstance()

        binding.googleSign.setOnClickListener {
            onGoogleSignClick()
        }

        binding.signWithGoogleText.setOnClickListener {
            onGoogleSignClick()
        }

        binding.appleSign.setOnClickListener {
            onAppleSignClick()
        }

        binding.signWithAppleText.setOnClickListener {
            onAppleSignClick()
        }

        binding.signInButton.setOnClickListener {
            onSignInButtonClick()
        }

        binding.loginText.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_login, LoginFragment.newInstance())
                .addToBackStack("SIGN_IN")
                .commit()
        }
    }

    private fun onSignInButtonClick() {
        val name = firstName.text.toString().trim().lowercase().replaceFirstChar { it.uppercaseChar() }
        val surname = lastName.text.toString().trim().lowercase().replaceFirstChar { it.uppercaseChar() }
        val emailInput = email.text.toString().trim().lowercase()
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        when {
            TextUtils.isEmpty(name) ->
                Toast.makeText(
                    requireContext(),
                    "Please, enter name",
                    Toast.LENGTH_SHORT
                ).show()
            TextUtils.isEmpty(emailInput) ->
                Toast.makeText(
                    requireContext(),
                    "Please, enter email",
                    Toast.LENGTH_SHORT
                ).show()

            TextUtils.isEmpty(surname) ->
                Toast.makeText(
                    requireContext(),
                    "Please, enter last name",
                    Toast.LENGTH_SHORT
                ).show()
            else -> {
                if(emailInput.matches(emailPattern.toRegex())) {
                    username = buildString {
                        append(firstName)
                        append(" ")
                        append(lastName)
                    }
                    createAccount(emailInput, PASSWORD)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please, enter valid email address",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun createAccount(email: String, password: String) {
        authentification.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    val user = authentification.currentUser
                    val profileUpdates = userProfileChangeRequest {
                        displayName = username
                    }

                    user!!.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d(TAG, "User profile updated.")
                            }
                        }
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    requireActivity().finish()

                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireContext(), "User with this name already exists",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


    private fun onGoogleSignClick() {
        Toast.makeText(requireContext(), "SIGN IN WITH GOOGLE", Toast.LENGTH_SHORT).show()
    }

    private fun onAppleSignClick() {
        Toast.makeText(requireContext(), "SIGN IN WITH APPLE", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignInFragment()
    }
}
