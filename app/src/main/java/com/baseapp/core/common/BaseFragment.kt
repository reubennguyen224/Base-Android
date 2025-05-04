package com.baseapp.core.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.baseapp.core.extensions.findClass
import com.baseapp.core.extensions.getBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    protected val binding
        get() = _binding
            ?: throw IllegalStateException("Must use ViewBinding after onCreateView and before onDestroyView")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initListener()
    }

    protected open fun initView() {}
    protected open fun initData() {}
    protected open fun initListener() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

private fun <VB : ViewBinding> BaseFragment<VB>.getBinding(
    inflater: LayoutInflater, container: ViewGroup?
): VB {
    return findClass().getBinding(inflater, container)
}