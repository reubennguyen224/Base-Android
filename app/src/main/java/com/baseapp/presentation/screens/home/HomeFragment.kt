package com.baseapp.presentation.screens.home

import com.baseapp.core.common.BaseFragment
import com.baseapp.core.extensions.observer
import com.baseapp.core.extensions.setSafeClickListener
import com.baseapp.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModel()

    override fun initListener() {
        super.initListener()
        binding.btnLogout.setSafeClickListener {
            viewModel.logout()
        }
    }

    override fun initData() {
        super.initData()
        viewModel.uiEvent.observer(viewLifecycleOwner) {
            when (it) {
                HomeUiEvent.LogoutSuccess -> {
                    showToast("Logout success")
                }
            }
        }
    }
}