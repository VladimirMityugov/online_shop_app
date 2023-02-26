package com.example.onlineShopApp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chocky_development.onlineShopApp.R
import com.example.onlineShopApp.ui.login.LoginFragment
import com.example.onlineShopApp.ui.login.SignInFragment
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_login, SignInFragment.newInstance())
                .commitNow()
        }
    }
}