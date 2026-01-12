package com.baseapp.presentation.view.insets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.baseapp.R
import androidx.core.content.withStyledAttributes

open class InsetsLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {

    init {
        context.withStyledAttributes(attrs, R.styleable.InsetsLinearLayout) {
            val type = getInt(R.styleable.InsetsLinearLayout_insetsType, -1)
            val padding = getInt(R.styleable.InsetsLinearLayout_insetsPadding, -1)
            val ignoringVisibility =
                getBoolean(R.styleable.InsetsLinearLayout_ignoringVisibility, true)
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