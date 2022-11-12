package com.aleexalvz.financeshelp.feature.home.lateststudies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LatestStudiesViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LatestStudiesViewModel() as T
    }
}