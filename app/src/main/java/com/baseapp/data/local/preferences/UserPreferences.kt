package com.baseapp.data.local.preferences

import android.content.Context
import androidx.core.content.edit
import com.baseapp.core.extensions.ioContext
import com.baseapp.core.extensions.preferences
import org.koin.core.annotation.Single

@Single
class UserPreferences(context: Context) {
    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    var email: String by prefs.preferences("user_email", "")
    var password: String by prefs.preferences("user_password", "")

    fun isLogged() = email.isEmpty()

    suspend fun clear() {
        ioContext {
            prefs.edit(true) { clear() }
        }
    }
}