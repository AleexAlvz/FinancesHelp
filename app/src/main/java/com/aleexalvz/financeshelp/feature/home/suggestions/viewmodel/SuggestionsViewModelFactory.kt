package com.aleexalvz.financeshelp.feature.home.suggestions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleexalvz.financeshelp.feature.home.lateststudies.viewmodel.LatestStudiesViewModel

class SuggestionsViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SuggestionsViewModel() as T
    }
}