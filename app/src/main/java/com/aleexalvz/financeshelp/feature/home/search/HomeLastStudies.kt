package com.aleexalvz.financeshelp.feature.home.search

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.aleexalvz.financeshelp.R

class HomeLastStudies(
    context: Context,
    attrs: AttributeSet
) : CardView(context, attrs) {

    init {
        inflate(context, R.layout.home_last_studies, this)
    }
}