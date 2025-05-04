package com.baseapp.core.navigation

sealed class NavigationRoutes(val route: String) {
    data object First: NavigationRoutes("FirstScreen")
    data object Login: NavigationRoutes("LoginScreen")
    data object Register: NavigationRoutes("RegisterScreen")
    data object Main: NavigationRoutes("MainScreen")
}