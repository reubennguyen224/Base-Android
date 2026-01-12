package com.baseapp.core.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.baseapp.R
import com.baseapp.core.common.BindingActivity

abstract class NavigationActivity<VB: ViewBinding>: BindingActivity<VB>() {
    protected lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
        initView()
        initData()
        initListener()
    }

    protected open fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = createMainNavGraph(navController)
        navController.graph = navGraph
    }

    protected open fun initView() {}
    protected open fun initData() {}
    protected open fun initListener() {}
}