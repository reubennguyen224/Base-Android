package com.baseapp.core.extensions

import android.os.SystemClock
import android.view.View

inline fun View.setSafeClickListener(interval: Int = 500, crossinline onSafeClick: (View) -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        private var lastTimeClicked: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastTimeClicked < interval) {
                return
            }
            lastTimeClicked = SystemClock.elapsedRealtime()
            onSafeClick(v)
        }
    })
}