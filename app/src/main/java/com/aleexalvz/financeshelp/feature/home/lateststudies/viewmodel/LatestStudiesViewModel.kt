package com.aleexalvz.financeshelp.feature.home.lateststudies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleexalvz.financeshelp.commons.model.Course
import com.aleexalvz.financeshelp.feature.home.lateststudies.model.LatestStudiesViewState
import java.lang.Exception

class LatestStudiesViewModel: ViewModel() {

    private val mutableViewState = MutableLiveData<LatestStudiesViewState>()
    val viewState: LiveData<LatestStudiesViewState> = mutableViewState

    fun getLatestStudies() {

        mutableViewState.postValue(LatestStudiesViewState.FetchingCourses)

        try {
            val lastStudies = mutableListOf<Course>(
                Course(
                    1,
                    "Curso 1: Primeiros passos",
                    "Aprenda como iniciar no mundo financeiro",
                    34,
                    false),
                Course(
                    2,
                    "Curso 2: Avançando",
                    "Continue seu aprendizado aprendendo novas coisas",
                    0,
                    false),
                Course(
                    3,
                    "Curso 3: Dominando o mundo financeiro",
                    "Se especializando no mundo financeiro, possibilitando maior independencia financeira",
                    100,
                    true)
            )

            mutableViewState.postValue(LatestStudiesViewState.FetchedCourses(lastStudies))
        } catch (exception: Exception){
            mutableViewState.postValue(LatestStudiesViewState.FailureInFetchCourses)
        }

    }
}