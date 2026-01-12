package com.baseapp.presentation.screens.first

import androidx.navigation.fragment.NavHostFragment
import com.baseapp.R
import com.baseapp.core.navigation.NavigationActivity
import com.baseapp.core.navigation.createFirstNavGraph
import com.baseapp.databinding.ActivityFirstBinding

class FirstActivity: NavigationActivity<ActivityFirstBinding>() {

    override fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = createFirstNavGraph(navController)
        navController.graph = navGraph
    }
}