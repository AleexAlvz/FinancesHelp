package com.aleexalvz.financeshelp.feature.home.lateststudies.model

import com.aleexalvz.financeshelp.commons.model.Course

sealed class LatestStudiesViewState {
    data class FetchedCourses(val courseList: List<Course>) : LatestStudiesViewState()
    object FailureInFetchCourses : LatestStudiesViewState()
    object FetchingCourses : LatestStudiesViewState()
}