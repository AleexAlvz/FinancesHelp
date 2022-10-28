package com.aleexalvz.financeshelp.feature.home.header

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.aleexalvz.financeshelp.R

class HeaderView(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.header_view, this)
        initViews()
    }

    private fun initViews() {
    }
}