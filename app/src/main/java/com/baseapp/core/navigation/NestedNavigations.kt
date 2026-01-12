package com.baseapp.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import com.baseapp.presentation.screens.first.FirstFragment
import com.baseapp.presentation.screens.home.HomeFragment
import com.baseapp.presentation.screens.login.LoginFragment

fun createFirstNavGraph(navController: NavController) = navController.createGraph(
    startDestination = FirstNavigationRoutes.First.route,
) {
    // add screen here
    addFirstFragment()
    addLoginFragment()
}

fun createMainNavGraph(navController: NavController) = navController.createGraph(
    startDestination = MainNavigationRoutes.Main.route,
) {
    // add screen here
    addHomeFragment()
}

// add screen method
private fun NavGraphBuilder.addFirstFragment() {
    fragment<FirstFragment>(FirstNavigationRoutes.First.route) {
        // can use arguments, deeplink, label in here

    }
}

private fun NavGraphBuilder.addLoginFragment() {
    fragment<LoginFragment>(FirstNavigationRoutes.Login.route) {
        // can use arguments, deeplink, label in here
    }
}

private fun NavGraphBuilder.addHomeFragment() {
    fragment<HomeFragment>(MainNavigationRoutes.Main.route) {
        // can use arguments, deeplink, label in here
    }
}