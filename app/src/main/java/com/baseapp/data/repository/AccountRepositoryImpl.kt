package com.baseapp.data.repository

import com.baseapp.core.extensions.stateFlow
import com.baseapp.data.api.Response
import com.baseapp.data.local.preferences.UserPreferences
import com.baseapp.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single(binds = [AccountRepository::class])
class AccountRepositoryImpl(
    private val userPreferences: UserPreferences,
): AccountRepository {
    private val signInState = stateFlow(userPreferences.email.isNotEmpty())

    override val isSignIn: Flow<Boolean>
        get() = signInState

    override fun login(email: String, password: String): Response<Boolean> {
        userPreferences.email = email
        userPreferences.password = password
        return Response(200, true)
    }

    override fun logout(): Response<Boolean> {
        userPreferences.email = ""
        userPreferences.password = ""
        return Response(200, true)
    }
}