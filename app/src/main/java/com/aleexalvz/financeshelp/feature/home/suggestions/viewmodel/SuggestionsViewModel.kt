package com.aleexalvz.financeshelp.feature.home.suggestions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleexalvz.financeshelp.commons.model.Course
import com.aleexalvz.financeshelp.feature.home.lateststudies.model.LatestStudiesViewState
import com.aleexalvz.financeshelp.feature.home.suggestions.model.SuggestionsViewState
import java.lang.Exception

class SuggestionsViewModel: ViewModel() {

    private val mutableViewState = MutableLiveData<SuggestionsViewState>()
    val viewState: LiveData<SuggestionsViewState> = mutableViewState

    fun getSuggestedCourses() {

        mutableViewState.postValue(SuggestionsViewState.FetchingCourses)

        try {
            val lastStudies = mutableListOf<Course>(
                Course("Calculos com porcentagem",
                    "Aprenda como fazer calculos com porcentagem e tenha uma visão de como está sua vida financeira de forma mais clara.",
                    100,
                    true),
                Course("CDB: O que é?",
                    "Descubra o que é CDB e o por que investir nele",
                    0,
                    false),
                Course("O quanto preciso economizar?",
                    "Entenda o quanto você precisa economizar para ter segurança e alcançar seus objetivos",
                    80,
                    false)
            )

            mutableViewState.postValue(SuggestionsViewState.FetchedCourses(lastStudies))
        } catch (exception: Exception){
            mutableViewState.postValue(SuggestionsViewState.FailureInFetchCourses)
        }

    }
}