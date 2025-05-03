package com.baseapp.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Insets
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowInsets
import androidx.fragment.app.Fragment

inline fun <reified T : Activity> Context.startActivity(extras: Bundle? = null, flag: Int? = null) {
    startActivity(Intent(this, T::class.java).apply {
        extras?.let { putExtras(it) }
        flag?.let { flags = flag }
    })
}

inline fun <reified T : Activity> Fragment.startActivity(
    extras: Bundle? = null,
    flag: Int? = null
) {
    startActivity(Intent(requireContext(), T::class.java).apply {
        extras?.let { putExtras(it) }
        flag?.let { flags = flag }
    })
}

fun Context.sendBroadcast(action: String, extras: Bundle? = null) {
    sendBroadcast(Intent(action).apply { extras?.let { putExtras(it) } })
}

fun Activity.getWidthDevice(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets: Insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        windowMetrics.bounds.width() - insets.left - insets.right
    } else {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.widthPixels
    }
}