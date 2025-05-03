package com.baseapp.core.extensions

import android.graphics.Color
import android.os.Build
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Window.makeFullScreen() {
    WindowCompat.setDecorFitsSystemWindows(this, false)
    val controller = WindowInsetsControllerCompat(this, decorView)
    controller.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    controller.show(WindowInsetsCompat.Type.systemBars())
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        isNavigationBarContrastEnforced = false
        isStatusBarContrastEnforced = false
        navigationBarColor = Color.TRANSPARENT
        statusBarColor = Color.TRANSPARENT
    } else {
        statusBarColor = Color.TRANSPARENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            navigationBarColor = Color.TRANSPARENT
    }
}