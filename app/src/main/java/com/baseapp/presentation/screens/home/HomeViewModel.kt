package com.baseapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baseapp.core.extensions.eventFlow
import com.baseapp.data.api.onSuccess
import com.baseapp.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val accountRepository: AccountRepository
): ViewModel() {

    val uiEvent = eventFlow<HomeUiEvent>()

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.logout()
                .onSuccess {
                    uiEvent.send(HomeUiEvent.LogoutSuccess)
                }
        }
    }
}

sealed class HomeUiEvent {
    object LogoutSuccess: HomeUiEvent()
}