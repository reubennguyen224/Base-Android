package com.baseapp.domain.repository

import com.baseapp.data.api.Response
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    val isSignIn: Flow<Boolean>

    fun login(email: String, password: String): Response<Boolean>

    fun logout(): Response<Boolean>
}