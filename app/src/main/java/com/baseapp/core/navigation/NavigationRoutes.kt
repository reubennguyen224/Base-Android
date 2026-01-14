package com.baseapp.core.navigation

sealed class FirstNavigationRoutes(val route: String) {
    data object First: FirstNavigationRoutes("FirstScreen")
    data object Login: FirstNavigationRoutes("LoginScreen")
    data object Register: FirstNavigationRoutes("RegisterScreen")
}

sealed class MainNavigationRoutes(val route: String) {
    data object Main: MainNavigationRoutes("MainScreen")
}