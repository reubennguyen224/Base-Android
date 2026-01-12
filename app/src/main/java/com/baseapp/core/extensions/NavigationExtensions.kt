package com.baseapp.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavHostController

fun NavHostController.navigateTo(route: String, isInclusive: Boolean = false) {
    navigate(route = route) {
        currentDestination?.id?.let { id ->
            popUpTo(id) {
                inclusive = isInclusive
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

inline fun <reified T: Any> NavController.observerResult(
    key: String,
    owner: LifecycleOwner,
    crossinline  onResult: (T) -> Unit
) {
    currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)?.observe(owner, object : Observer<T> {
        override fun onChanged(value: T) {
            onResult(value)
            currentBackStackEntry?.savedStateHandle?.remove<T>(key)
            currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)?.removeObserver(this)
        }
    })
}

inline fun <reified T: Any> NavController.setResult(key: String, value: T) {
    previousBackStackEntry?.savedStateHandle?.set(key, value)
}