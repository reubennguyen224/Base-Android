package com.baseapp.presentation.screens.login

import com.baseapp.core.common.BaseFragment
import com.baseapp.core.extensions.observer
import com.baseapp.core.extensions.setSafeClickListener
import com.baseapp.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: BaseFragment<FragmentLoginBinding>() {
    private val viewModel: LoginViewModel by viewModel()

    override fun initView() {
        super.initView()
        binding.run {
            btnLogin.setSafeClickListener {
                viewModel.login(
                    email = etEmail.text.toString().trim(),
                    password = etPassword.text.toString().trim()
                )
            }
        }
    }

    override fun initData() {
        super.initData()
        viewModel.uiState.observer(viewLifecycleOwner) {

        }

        viewModel.uiEvent.observer(viewLifecycleOwner) {
            when (it) {
                LoginUiEvent.LoginSuccess -> {
                    showToast("Login success")
                }
                LoginUiEvent.EmptyEmail -> {
                    showToast("Empty email")
                }
                LoginUiEvent.InvalidEmail -> {
                    showToast("Invalid email")
                }
                LoginUiEvent.TooShortPassword -> {
                    showToast("Too short password")
                }
            }
        }
    }
}