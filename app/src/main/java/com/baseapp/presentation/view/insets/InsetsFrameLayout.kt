package com.baseapp.presentation.view.insets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.baseapp.R
import androidx.core.content.withStyledAttributes

open class InsetsFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    init {
        context.withStyledAttributes(attrs, R.styleable.InsetsFrameLayout) {
            val type = getInt(R.styleable.InsetsFrameLayout_insetsType, -1)
            val padding = getInt(R.styleable.InsetsFrameLayout_insetsPadding, -1)
            val ignoringVisibility =
                getBoolean(R.styleable.InsetsFrameLayout_ignoringVisibility, true)
            if (type != -1 && padding != -1) {
                applySystemWindowInsetsPadding(
                    applyLeft = padding and PADDING_LEFT == PADDING_LEFT,
                    applyTop = padding and PADDING_TOP == PADDING_TOP,
                    applyRight = padding and PADDING_RIGHT == PADDING_RIGHT,
                    applyBottom = padding and PADDING_BOTTOM == PADDING_BOTTOM,
                    ignoringVisibility = ignoringVisibility,
                    type = type
                )
            }
        }
    }
}