package com.aleexalvz.financeshelp.commons.model

sealed class LatestStudiesViewState {
    data class FetchedCourses(val courseList: List<Course>) : LatestStudiesViewState()
    object FailureInFetchCourses : LatestStudiesViewState()
    object FetchingCourses : LatestStudiesViewState()
}