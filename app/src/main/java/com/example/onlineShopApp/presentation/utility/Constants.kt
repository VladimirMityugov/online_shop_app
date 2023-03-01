package com.example.onlineShopApp.presentation.utility

import android.Manifest
import android.os.Build


object Constants {

    const val PASSWORD = "password"
    const val JACK_SPARROW_IMAGE_URL =
        "https://w0.peakpx.com/wallpaper/458/418/HD-wallpaper-captain-jack-sparrow-fantasy-luminos-man-face-mark-armstrong-rand-johnny-depp.jpg"
    const val SCALE_VALUE_FOR_ANIMATION = 1.5F
    const val TRANSLATION_VALUE_INITIAL_FOR_ANIMATION = 0F
    const val TRANSLATION_VALUE_FINAL_FOR_ANIMATION = 20F
    const val ELEVATION_VALUE_FOR_ANIMATION = 20F
    val REQUIRED_PERMISSIONS: Array<String> = buildList {
        add(Manifest.permission.READ_EXTERNAL_STORAGE)
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {
            add(Manifest.permission.READ_MEDIA_AUDIO)
        }
    }.toTypedArray()
}