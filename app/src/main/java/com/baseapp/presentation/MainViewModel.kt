package com.baseapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baseapp.core.extensions.stateFlow
import com.baseapp.domain.repository.AccountRepository
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val accountRepository: AccountRepository
) : ViewModel() {
    val appState = stateFlow(AppState())

    init {
        reloadSignInState()
    }

    fun reloadSignInState() {
        viewModelScope.launch {
            accountRepository.isSignIn.collect { isSignIn ->
                appState.update { it.copy(isSignIn = isSignIn) }
            }
        }
    }
}

data class AppState(
    val isSignIn: Boolean = true
)