package com.baseapp.core.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.baseapp.R
import com.baseapp.core.extensions.findClass
import com.baseapp.core.extensions.getBinding
import com.baseapp.core.navigation.createNavGraph

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var navController: NavController
    private var _binding: VB? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Must use viewbinding before onDestroy and after onCreate")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getBinding()
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = createNavGraph(navController)
        navController.graph = navGraph

        initView()
        initData()
        initListener()
    }

    protected open fun initView() {}
    protected open fun initData() {}
    protected open fun initListener() {}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

private fun <VB : ViewBinding> BaseActivity<VB>.getBinding(): VB {
    return findClass().getBinding(layoutInflater)
}