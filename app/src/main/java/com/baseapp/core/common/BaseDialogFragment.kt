package com.baseapp.core.common

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.baseapp.R
import com.baseapp.core.extensions.findClass
import com.baseapp.core.extensions.getBinding

abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment() {
    private var _binding: VB? = null
    protected val binding
        get() = _binding
            ?: throw IllegalStateException("Must use ViewBinding after onCreateView and before onDestroyView")
    protected open val options = Options()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (options.isFullScreen) {
            setStyle(STYLE_NO_TITLE, R.style.full_screen_dialog)
        }
        isCancelable = options.cancelable
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), theme)
        dialog.setCancelable(options.cancelable)
        dialog.setCanceledOnTouchOutside(options.cancelable)
        if (options.dimAmount > 0) {
            dialog.window?.setDimAmount(options.dimAmount)
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parseData(savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initListener()
    }

    override fun onResume() {
        val window = dialog?.window
        val resource = requireContext().resources
        val metrics = resource.displayMetrics
        val params = window?.attributes

        params?.let {
            options.marginHorizontal?.let {
                val marginRL = it
                params.width = metrics.widthPixels - 2 * marginRL
            } ?: run {
                params.width = options.width
            }
            options.marginVertical?.let {
                val marginRL = it
                params.height = metrics.heightPixels - 2 * marginRL
            } ?: run {
                params.height = options.height
            }
            window.setLayout(params.width, params.height)
            window.setGravity(Gravity.CENTER)
            options.backgroundDrawable?.let {
                window.setBackgroundDrawableResource(it)
            } ?: run {
                window.setBackgroundDrawableResource(android.R.color.transparent)
            }
        }
        super.onResume()
    }

    protected open fun initView() {}
    protected open fun initData() {}
    protected open fun initListener() {}
    protected open fun parseData(bundle: Bundle?) {}

    data class Options(
        val isFullScreen: Boolean = false,
        val cancelable: Boolean = true,
        val height: Int = ViewGroup.LayoutParams.MATCH_PARENT,
        val width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
        val marginHorizontal: Int? = null,
        val marginVertical: Int? = null,
        val backgroundDrawable: Int? = null,
        val dimAmount: Float = -1f
    )
}

private fun <VB : ViewBinding> BaseDialogFragment<VB>.getBinding(
    inflater: LayoutInflater, container: ViewGroup?
): VB {
    return findClass().getBinding(inflater, container)
}