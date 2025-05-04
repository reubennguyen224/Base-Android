package com.baseapp.presentation.screens.first

import com.baseapp.core.common.BaseFragment
import com.baseapp.core.navigation.NavigationRoutes
import com.baseapp.databinding.FragmentFirstBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment: BaseFragment<FragmentFirstBinding>() {
    private val viewModel: FirstViewModel by viewModel()

    override fun initView() {
        super.initView()
    }

    override fun initData() {
        super.initData()
    }

    override fun initListener() {
        super.initListener()
        binding.btnLogin.setOnClickListener {
            navController.navigate(NavigationRoutes.Login.route)
        }
    }
}