package com.example.onlineShopApp.presentation.utility

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.example.onlineShopApp.presentation.utility.Constants.REQUIRED_PERMISSIONS

fun Context.hasReadPermission(): Boolean {

    return REQUIRED_PERMISSIONS.all { permission ->
           ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

}
