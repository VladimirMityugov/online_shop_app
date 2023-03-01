package com.example.onlineShopApp.presentation.utility

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.onlineShopApp.presentation.utility.Constants.REQUIRED_PERMISSIONS

private const val TAG = "LOCATION_PERMISSION"

fun Context.hasReadPermission(): Boolean {

    return REQUIRED_PERMISSIONS.all { permission ->
        Log.d(
            TAG, "Permission $permission is: ${
                ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }"
        )
        ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

}
