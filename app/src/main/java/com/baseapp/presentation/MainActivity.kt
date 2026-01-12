package com.baseapp.presentation

import com.baseapp.core.extensions.observer
import com.baseapp.core.extensions.startActivity
import com.baseapp.core.navigation.NavigationActivity
import com.baseapp.databinding.ActivityMainBinding
import com.baseapp.presentation.screens.first.FirstActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : NavigationActivity<ActivityMainBinding>() {
    private val viewmodel: MainViewModel by viewModel()

    override fun initView() {
        super.initView()

    }

    override fun initData() {
        super.initData()
        viewmodel.appState.observer(this) {
            if (it.isSignIn.not()) {
                startActivity<FirstActivity>()
                finish()
            }
        }
    }

    override fun initListener() {
        super.initListener()

    }
}