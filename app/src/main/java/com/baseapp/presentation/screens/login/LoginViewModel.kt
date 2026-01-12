package com.baseapp.presentation.screens.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baseapp.core.extensions.eventFlow
import com.baseapp.core.extensions.stateFlow
import com.baseapp.data.api.onSuccess
import com.baseapp.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val accountRepository: AccountRepository
): ViewModel() {
    val uiState = stateFlow(LoginUiState())
    val uiEvent = eventFlow<LoginUiEvent>()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkValidEmail(email = email) && checkValidPassword(password = password)) {
                uiState.update { it.copy(isLoading = true) }
                accountRepository.login(email, password)
                    .onSuccess {
                        uiEvent.trySend(LoginUiEvent.LoginSuccess)
                    }
            }
        }
    }

    fun checkValidEmail(email: String): Boolean {
        return when {
            email.isEmpty() -> {
                uiEvent.trySend(LoginUiEvent.EmptyEmail)
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                uiEvent.trySend(LoginUiEvent.InvalidEmail)
                false
            }
            else -> true
        }
    }

    fun checkValidPassword(password: String): Boolean {
        return if (password.length >= MIN_LENGTH_PASSWORD) {
            true
        } else {
            uiEvent.trySend(LoginUiEvent.TooShortPassword)
            false
        }
    }

    companion object {
        const val MIN_LENGTH_PASSWORD = 6
    }
}

data class LoginUiState(val isLoading: Boolean = false)

sealed class LoginUiEvent {
    object TooShortPassword : LoginUiEvent()
    object EmptyEmail : LoginUiEvent()
    object InvalidEmail : LoginUiEvent()
    object LoginSuccess: LoginUiEvent()
}