package com.baseapp.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import com.baseapp.presentation.screens.first.FirstFragment
import com.baseapp.presentation.screens.login.LoginFragment

fun createNavGraph(navController: NavController) = navController.createGraph(
    startDestination = NavigationRoutes.First.route,
) {
    // add screen here
    addFirstFragment()
    addLoginFragment()
}

// add screen method
private fun NavGraphBuilder.addFirstFragment() {
    fragment<FirstFragment>(NavigationRoutes.First.route) {
        // can use arguments, deeplink, label in here
    }
}

private fun NavGraphBuilder.addLoginFragment() {
    fragment<LoginFragment>(NavigationRoutes.Login.route) {
        // can use arguments, deeplink, label in here
    }
}