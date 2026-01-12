package com.baseapp.core.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.baseapp.core.extensions.findClass
import com.baseapp.core.extensions.getBinding

abstract class BindingActivity<VB : ViewBinding> : AppCompatActivity() {
    private var _binding: VB? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Must use viewbinding before onDestroy and after onCreate")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getBinding()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

private fun <VB : ViewBinding> BindingActivity<VB>.getBinding(): VB {
    return findClass().getBinding(layoutInflater)
}