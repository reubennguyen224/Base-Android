package com.baseapp.core.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.withContext

fun <T> Flow<T>.observer(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.CREATED,
    action: suspend (T) -> Unit
) {
    flowWithLifecycle(owner.lifecycle, minActiveState)
        .onEach(action)
        .launchIn(owner.lifecycleScope)
}

fun <T> Channel<T>.observer(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.CREATED,
    action: suspend (T) -> Unit
) {
    receiveAsFlow()
        .observer(owner, minActiveState, action)
}

suspend fun <T> ioContext(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.IO, block)

inline fun <reified T> stateFlow(initialValue: T) = MutableStateFlow(initialValue)
inline fun <reified T> eventFlow() = Channel<T>(onBufferOverflow = BufferOverflow.DROP_LATEST)